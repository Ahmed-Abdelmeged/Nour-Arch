package com.abdelmeged.ahmed.nourplayerarch.ui

import android.Manifest
import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.widget.LinearLayoutManager
import com.abdelmeged.ahmed.nourplayerarch.R
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import com.abdelmeged.ahmed.nourplayerarch.ui.adapter.SuraClickCallbacks
import com.abdelmeged.ahmed.nourplayerarch.ui.adapter.SuraDownloadClickCallbacks
import com.abdelmeged.ahmed.nourplayerarch.ui.adapter.SuraRecyclerAdapter
import com.abdelmeged.ahmed.nourplayerarch.viewmodel.SuraViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.content.IntentFilter
import com.abdelmeged.ahmed.nourplayerarch.model.Download
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Toolbar
import com.abdelmeged.ahmed.nourplayerarch.utils.*
import net.gotev.speech.Speech
import net.gotev.speech.SpeechDelegate
import org.jetbrains.anko.toast
import net.gotev.speech.GoogleVoiceTypingDisabledException
import net.gotev.speech.SpeechRecognitionNotAvailable
import java.util.*


class MainActivity : LifecycleActivity(), SuraClickCallbacks, SuraDownloadClickCallbacks, SpeechDelegate {
    val LOG_TAG: String? = MainActivity::class.java.simpleName

    private lateinit var suraRecyclerAdapter: SuraRecyclerAdapter

    lateinit var suraViewModel: SuraViewModel

    private val WRITE_PERMISSION_REQUEST_CODE = 523
    private val REQUEST_MICROPHONE = 524


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val speechLifeCycleObserver = SpeechLifeCycleObserver(this, this)

        suraViewModel = ViewModelProviders.of(this).get(SuraViewModel::class.java)

        //Init recycler view
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        sura_recycler.layoutManager = layoutManager
        sura_recycler.setHasFixedSize(true)
        suraRecyclerAdapter = SuraRecyclerAdapter(this, this, this)
        sura_recycler.adapter = suraRecyclerAdapter

        //Get the suras from the database
        suraViewModel.suras.observe(this, Observer<List<Sura>> {
            suras ->
            suraRecyclerAdapter.setSuras(suras as ArrayList<Sura>)
        })

        voice_recognition_fab.setOnClickListener({
            if (Utilities.isAvailableInternetConnection(this)) {
                if (Speech.getInstance().isListening) {
                    Speech.getInstance().stopListening()
                } else {
                    if (!Utilities.checkMicrophonePermission(this)) {
                        ActivityCompat.requestPermissions(this,
                                arrayOf(Manifest.permission.RECORD_AUDIO), REQUEST_MICROPHONE)
                    } else {
                        onRecordAudioPermissionGranted()
                    }
                }
            } else {
                toast("Check your internet connection")
            }
        })

        //Register a receiver for download item
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.MESSAGE_PROGRESS)
        val receiverManger = ReceiverManager(LocalBroadcastManager.getInstance(this)
                , this, broadcastReceiver, intentFilter)
    }

    override fun onSuraDownloadClick(sura: Sura) {
        if (Utilities.checkWriteStoragePermission(this)) {
            suraViewModel.downloadSuras(sura)
        } else {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    WRITE_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onSuraClick(sura: Sura) {
        val playSuraIntent = Intent(this@MainActivity, SuraPlayerActivity::class.java)
        if (FilesUtils.isSuraDownloaded(sura.quranIndex, this)) {
            sura.downloadUrl = FilesUtils.getSuraUri(sura.quranIndex, this)
            playSuraIntent.putExtra(Constants.EXTRA_SURA, sura)
        } else {
            playSuraIntent.putExtra(Constants.EXTRA_SURA, sura)
        }
        startActivity(playSuraIntent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            WRITE_PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
            }
            REQUEST_MICROPHONE -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                toast(R.string.permission_required)
            }
        }
    }


    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Constants.MESSAGE_PROGRESS) {
                val download = intent.getParcelableExtra<Download>("download")
                if (download.progress == 100) {
                    suraRecyclerAdapter.notifyDataSetChanged()
                } else {
                }
            }
        }
    }

    private fun onRecordAudioPermissionGranted() {
        voice_recognition_fab.visibility = View.GONE
        speech_progress_container.visibility = View.VISIBLE

        try {
            Speech.getInstance().stopTextToSpeech()
            Speech.getInstance().startListening(speech_progress_view, this@MainActivity)

        } catch (exc: SpeechRecognitionNotAvailable) {
            Utilities.showSpeechNotSupportedDialog(this)

        } catch (exc: GoogleVoiceTypingDisabledException) {
            Utilities.showEnableGoogleVoiceTyping(this)
        }
    }

    override fun onStartOfSpeech() {
    }

    override fun onSpeechPartialResults(results: MutableList<String>?) {
    }

    override fun onSpeechRmsChanged(value: Float) {
    }

    override fun onSpeechResult(result: String?) {
        voice_recognition_fab.visibility = View.VISIBLE;
        speech_progress_container.visibility = View.GONE;

        if (result!!.isEmpty()) {
            Speech.getInstance().say(getString(R.string.repeat))
        }
        val currentQuranIndex = Utilities.getCurrentQuerySura(result)
        if (currentQuranIndex != null) {
            suraViewModel.getSura(currentQuranIndex).observe(this, Observer {
                sura ->
                if (sura != null) {
                    startPlayerActivity(sura)
                } else {
                    toast("This sura not found in the database")
                }
            })
        } else {
            toast("No sura found")
        }
    }

    fun startPlayerActivity(sura: Sura) {
        val playSuraIntent = Intent(this, SuraPlayerActivity::class.java)
        if (FilesUtils.isSuraDownloaded(sura.quranIndex, this)) {
            sura.downloadUrl = FilesUtils.getSuraUri(sura.quranIndex, this)
            playSuraIntent.putExtra(Constants.EXTRA_SURA, sura)
        } else {
            playSuraIntent.putExtra(Constants.EXTRA_SURA, sura)
        }
        startActivity(playSuraIntent)
    }
}


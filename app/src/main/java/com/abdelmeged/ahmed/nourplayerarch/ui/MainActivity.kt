package com.abdelmeged.ahmed.nourplayerarch.ui

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
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants
import com.abdelmeged.ahmed.nourplayerarch.model.Download
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import com.abdelmeged.ahmed.nourplayerarch.utils.FilesUtils
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex
import com.abdelmeged.ahmed.nourplayerarch.utils.ReceiverManager


class MainActivity : LifecycleActivity(), SuraClickCallbacks, SuraDownloadClickCallbacks {

    val LOG_TAG: String? = MainActivity::class.java.simpleName

    private lateinit var suraRecyclerAdapter: SuraRecyclerAdapter

    lateinit var suraViewModel: SuraViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            FilesUtils.cleanAudioDownloadDirectory(this)
            suraRecyclerAdapter.notifyDataSetChanged()
        })

        //Register a receiver for download item
        val intentFilter = IntentFilter()
        intentFilter.addAction(Constants.MESSAGE_PROGRESS)
        val receiverManger = ReceiverManager(LocalBroadcastManager.getInstance(this)
                , this, broadcastReceiver, intentFilter)
    }

    override fun onSuraDownloadClick(sura: Sura) {
        suraViewModel.downloadSuras(sura)
    }

    override fun onSuraClick(sura: Sura) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

    fun add() {
        val suars = ArrayList<Sura>()
        suars.add(Sura(QuranIndex.BQR, "Al-Baqara", "0Bz9EiHndgROYLUU5anNWWGJsX1k"))
        suars.add(Sura(QuranIndex.KWA, "Al-Kawthar", "0Bz9EiHndgROYeHlFLTdiekFKREE"))
        suars.add(Sura(QuranIndex.ROM, "Ar-Room", "0Bz9EiHndgROYTndObTRsWlBZMTg"))
        suars.add(Sura(QuranIndex.MLK, "Al-Mulk", "0Bz9EiHndgROYZGVnMmFYaFd1WTg"))
        suraViewModel.addSuras(suars)
    }

}

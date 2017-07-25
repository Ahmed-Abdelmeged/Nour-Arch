package com.abdelmeged.ahmed.nourplayerarch.utils

import android.Manifest
import android.content.Context
import android.net.NetworkInfo
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.abdelmeged.ahmed.nourplayerarch.R
import com.abdelmeged.ahmed.nourplayerarch.R.string.enable_google_voice_typing
import com.abdelmeged.ahmed.nourplayerarch.R.string.speech_not_available
import net.gotev.speech.SpeechUtil
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.support.v4.content.ContextCompat
import android.util.Log
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import com.abdelmeged.ahmed.nourplayerarch.viewmodel.SuraViewModel
import java.util.*


/**
 * Created by ahmed on 7/24/2017.
 */
class Utilities {
    companion object {

        /**
         * Helper method to check the internet connection isAvailableInternetConnection
         */
        fun isAvailableInternetConnection(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected

        }

        fun showSpeechNotSupportedDialog(context: Context) {
            val dialogClickListener = DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> SpeechUtil.redirectUserToGoogleAppOnPlayStore(context)
                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }
            }

            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.speech_not_available)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes, dialogClickListener)
                    .setNegativeButton(R.string.no, dialogClickListener)
                    .show()
        }

        fun showEnableGoogleVoiceTyping(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.enable_google_voice_typing)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes, { dialogInterface, i ->
                        // do nothing
                    }).show()
        }

        fun checkWriteStoragePermission(context: Context): Boolean {
            val result = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
            return result == PackageManager.PERMISSION_GRANTED
        }

        fun checkMicrophonePermission(context: Context): Boolean {
            val result = ContextCompat.checkSelfPermission(context,
                    Manifest.permission.RECORD_AUDIO)
            return result == PackageManager.PERMISSION_GRANTED
        }

        /**
         * Helper method to get the sura index and details form the speech text
         */
        fun getCurrentQuerySura(result: String): QuranIndex? {
            val p = Arrays.asList(result.split(" "))
            var suarh: QuranIndex? = null
            var part = ""
            var aya = ""
            var hzeb = ""
            var page = ""
            var quarter = ""

            //find if is a part
            for (i in 0..p.size - 1) {
                if (Constants.PART.contains(p[i].toString())) {
                    try {
                        part = p[i + 1].toString()
                    } catch (e: IndexOutOfBoundsException) {
                    }
                }

                if (Constants.AYA.contains(p[i].toString())) {
                    try {
                        aya = p[i + 1].toString()
                    } catch (e: IndexOutOfBoundsException) {
                    }
                }

                if (Constants.PAGE.contains(p[i].toString())) {
                    try {
                        page = p[i + 1].toString()
                    } catch (e: IndexOutOfBoundsException) {
                    }
                }

                if (Constants.QUARTER.contains(p[i].toString())) {
                    try {
                        quarter = p[i + 1].toString()
                    } catch (e: IndexOutOfBoundsException) {
                    }
                }

                if (Constants.HZEB.contains(p[i].toString())) {
                    try {
                        hzeb = p[i + 1].toString()
                    } catch (e: IndexOutOfBoundsException) {
                    }
                }
            }

            //find thw sura form text
            surahLoop@ for (entry in Constants.sSurahs.entries) {
                val s = entry.value
                for (i in 0..p.size - 1) {
                    Log.e("s", p[i].toString().substring(1, p[i].toString().length - 1))
                    if (s.contains(p[i].toString().substring(1, p[i].toString().length - 1))) {
                        suarh = entry.key
                        break@surahLoop
                    }
                }
            }
            return suarh
        }

        /**
         * Method to insert fake data
         */
        fun add(suraViewModel: SuraViewModel) {
            val suars = ArrayList<Sura>()
            suars.add(Sura(QuranIndex.BQR, "Al-Baqara", "0Bz9EiHndgROYLUU5anNWWGJsX1k"))
            suars.add(Sura(QuranIndex.KWA, "Al-Kawthar", "0Bz9EiHndgROYeHlFLTdiekFKREE"))
            suars.add(Sura(QuranIndex.ROM, "Ar-Room", "0Bz9EiHndgROYTndObTRsWlBZMTg"))
            suars.add(Sura(QuranIndex.MLK, "Al-Mulk", "0Bz9EiHndgROYZGVnMmFYaFd1WTg"))
            suraViewModel.addSuras(suars)
        }

    }
}
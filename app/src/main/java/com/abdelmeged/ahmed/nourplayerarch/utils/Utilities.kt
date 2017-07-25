package com.abdelmeged.ahmed.nourplayerarch.utils

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
    }
}
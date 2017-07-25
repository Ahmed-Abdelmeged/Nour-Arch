package com.abdelmeged.ahmed.nourplayerarch.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import net.gotev.speech.Speech
import java.util.*

/**
 * Created by ahmed on 7/25/2017.
 */
class SpeechLifeCycleObserver(lifecycleOwner: LifecycleOwner, val context: Context) : LifecycleObserver {
    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun registerSpeech() {
        Speech.init(context)
        //set the speech language to arabic
        Speech.getInstance().setLocale(Locale(Constants.LOCALE_ARABIC))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unregisterSpeech() {
        Speech.getInstance().shutdown()
    }

}
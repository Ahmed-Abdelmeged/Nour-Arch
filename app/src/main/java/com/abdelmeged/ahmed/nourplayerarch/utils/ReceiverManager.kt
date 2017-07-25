package com.abdelmeged.ahmed.nourplayerarch.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager

/**
 * Created by ahmed on 7/25/2017.
 */
class ReceiverManager(val localBroadcastManager: LocalBroadcastManager, lifecycleOwner: LifecycleOwner,
                      val broadcastReceiver: BroadcastReceiver,
                      val intentFilter: IntentFilter) : LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun registerReceiver() {
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun unregisterReceiver() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver)
    }


}
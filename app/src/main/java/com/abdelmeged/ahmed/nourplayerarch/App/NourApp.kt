package com.abdelmeged.ahmed.nourplayerarch.App

import android.app.Application
import com.abdelmeged.ahmed.nourplayerarch.di.AppComponent
import com.abdelmeged.ahmed.nourplayerarch.di.AppModule
import com.abdelmeged.ahmed.nourplayerarch.di.DaggerAppComponent
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants
import com.abdelmeged.ahmed.nourplayerarch.utils.FilesUtils
import com.danikula.videocache.HttpProxyCacheServer
import net.gotev.speech.Speech
import java.util.*

/**
 * Created by ahmed on 7/24/2017.
 */
class NourApp : Application() {


    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    //lateinit var proxy: HttpProxyCacheServer

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        Speech.init(this)

        //set the speech language to arabic
        Speech.getInstance().setLocale(Locale(Constants.LOCALE_ARABIC))

        /*proxy = HttpProxyCacheServer.Builder(this)
                .cacheDirectory(FilesUtils.getAudioCacheDirectory(this))
                .build()*/
    }
}
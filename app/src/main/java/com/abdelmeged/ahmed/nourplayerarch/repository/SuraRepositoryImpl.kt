package com.abdelmeged.ahmed.nourplayerarch.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import android.content.Intent
import android.util.Log
import com.abdelmeged.ahmed.nourplayerarch.App.NourApp
import com.abdelmeged.ahmed.nourplayerarch.db.AppDatabase
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import com.abdelmeged.ahmed.nourplayerarch.di.AppComponent
import com.abdelmeged.ahmed.nourplayerarch.service.AudioDownloadService
import com.abdelmeged.ahmed.nourplayerarch.service.NourService
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex
import io.reactivex.Completable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by ahmed on 7/24/2017.
 */
class SuraRepositoryImpl : SuraRepository {
    @Inject
    lateinit var appDatabase: AppDatabase


    constructor(appDatabase: AppDatabase) {
        this.appDatabase = appDatabase
    }


    override fun getSuras(): LiveData<List<Sura>> {
        return appDatabase.suraDao().getAll()
    }

    override fun getSura(quranIndex: QuranIndex): LiveData<Sura> {
        return appDatabase.suraDao().getSura(quranIndex)
    }

    override fun addSuras(suras: List<Sura>): Completable {
        return Completable.fromAction { appDatabase.suraDao().insertAll(suras) }
    }

    override fun addSura(sura: Sura): Completable {
        return Completable.fromAction { appDatabase.suraDao().insert(sura) }
    }

    override fun deleteSuras(suras: List<Sura>): Completable {
        return Completable.fromAction { appDatabase.suraDao().deleteAll(suras) }
    }

    override fun deleteSura(sura: Sura): Completable {
        return Completable.fromAction { appDatabase.suraDao().delete(sura) }
    }

    override fun downloadSura(sura: Sura, context: Context) {
        val intent = Intent(context, AudioDownloadService::class.java)
        intent.putExtra(Constants.EXTRA_SURA_DOWNLOAD, sura)
        context.startService(intent)
    }

}
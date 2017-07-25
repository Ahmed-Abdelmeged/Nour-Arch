package com.abdelmeged.ahmed.nourplayerarch.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex
import io.reactivex.Completable
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created by ahmed on 7/24/2017.
 */
interface SuraRepository {

    fun getSuras(): LiveData<List<Sura>>

    fun getSura(quranIndex: QuranIndex): LiveData<Sura>

    fun addSuras(suras: List<Sura>): Completable

    fun addSura(sura: Sura): Completable

    fun deleteSuras(suras: List<Sura>): Completable

    fun deleteSura(sura: Sura): Completable

    fun downloadSura(sura: Sura, context: Context)
}
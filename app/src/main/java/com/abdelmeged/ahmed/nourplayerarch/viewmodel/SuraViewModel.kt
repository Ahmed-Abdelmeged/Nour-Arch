package com.abdelmeged.ahmed.nourplayerarch.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.abdelmeged.ahmed.nourplayerarch.App.NourApp
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import com.abdelmeged.ahmed.nourplayerarch.repository.SuraRepository
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by ahmed on 7/24/2017.
 */
class SuraViewModel(application: Application) : AndroidViewModel(application) {

    val LOG_TAG: String? = SuraViewModel::class.java.simpleName

    @Inject
    lateinit var suraRepository: SuraRepository

    var suras: LiveData<List<Sura>> = MutableLiveData()

    init {
        NourApp.appComponent.inject(this)
        suras = suraRepository.getSuras()
    }

    fun addSuras(suras: List<Sura>) {
        suraRepository.addSuras(suras)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        Log.e(LOG_TAG, "onError - add: " + e.localizedMessage)
                    }

                    override fun onComplete() {
                        Log.e(LOG_TAG, "onComplete - successfully added suras")
                    }
                })
    }

    fun getSura(quranIndex: QuranIndex): LiveData<Sura> {
        return suraRepository.getSura(quranIndex)
    }

    fun downloadSuras(sura: Sura) {
        suraRepository.downloadSura(sura, this.getApplication())
    }

}
package com.abdelmeged.ahmed.nourplayerarch.di

import com.abdelmeged.ahmed.nourplayerarch.repository.SuraRepositoryImpl
import com.abdelmeged.ahmed.nourplayerarch.service.AudioDownloadService
import com.abdelmeged.ahmed.nourplayerarch.ui.SuraPlayerActivity
import com.abdelmeged.ahmed.nourplayerarch.viewmodel.SuraViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by ahmed on 7/24/2017.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(suraViewModel: SuraViewModel)

    fun inject(audioDownloadService: AudioDownloadService)

    fun inject(suraPlayerActivity: SuraPlayerActivity)
}

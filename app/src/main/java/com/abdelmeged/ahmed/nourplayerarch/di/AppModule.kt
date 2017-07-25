package com.abdelmeged.ahmed.nourplayerarch.di

import android.arch.persistence.room.Room
import android.content.Context
import com.abdelmeged.ahmed.nourplayerarch.App.NourApp
import com.abdelmeged.ahmed.nourplayerarch.db.AppDatabase
import com.abdelmeged.ahmed.nourplayerarch.repository.SuraRepository
import com.abdelmeged.ahmed.nourplayerarch.repository.SuraRepositoryImpl
import com.abdelmeged.ahmed.nourplayerarch.service.NourService
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants
import com.abdelmeged.ahmed.nourplayerarch.utils.FilesUtils
import com.danikula.videocache.HttpProxyCacheServer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by ahmed on 7/24/2017.
 */

@Module
class AppModule(val nourApp: NourApp) {

    @Provides
    @Singleton
    fun applicationContext(): Context {
        return nourApp
    }

    @Provides
    @Singleton
    fun provideSuraRepository(appdatabase: AppDatabase): SuraRepository {
        return SuraRepositoryImpl(appdatabase)
    }

    @Provides
    @Singleton
    fun provideSuraDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, Constants.TABLE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideNourService(): NourService {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NourService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpProxyCacheServer(): HttpProxyCacheServer {
        return HttpProxyCacheServer.Builder(this.nourApp)
                .cacheDirectory(FilesUtils.getAudioCacheDirectory(this.nourApp))
                .build()
    }
}
package com.abdelmeged.ahmed.nourplayerarch.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.abdelmeged.ahmed.nourplayerarch.db.convertor.QuranIndexConverter
import com.abdelmeged.ahmed.nourplayerarch.db.dao.SuraDao
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura

/**
 * Created by ahmed on 7/24/2017.
 */

@Database(
        entities = arrayOf(Sura::class),
        version = 1)
@TypeConverters(QuranIndexConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun suraDao(): SuraDao
}
package com.abdelmeged.ahmed.nourplayerarch.db.convertor

import android.arch.persistence.room.TypeConverter
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex

/**
 * Created by ahmed on 7/24/2017.
 */
class QuranIndexConverter {

    @TypeConverter
    fun toQuranIndex(index: String): QuranIndex {
        return QuranIndex.valueOf(index)
    }

    @TypeConverter
    fun toStringIndex(quranIndex: QuranIndex): String {
        return quranIndex.name
    }
}
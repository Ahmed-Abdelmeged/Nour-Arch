package com.abdelmeged.ahmed.nourplayerarch.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants
import android.arch.persistence.room.Query
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex

/**
 * Created by ahmed on 7/24/2017.
 */

@Dao
interface SuraDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME)
    fun getAll(): LiveData<List<Sura>>

    @Query("SELECT * FROM " + Constants.TABLE_NAME + " WHERE quranIndex IN (:quranIndex)")
    fun getSura(quranIndex: QuranIndex): LiveData<Sura>

    @Insert
    fun insertAll(suras: List<Sura>)

    @Insert
    fun insert(sura: Sura)

    @Delete
    fun deleteAll(suras: List<Sura>)

    @Delete
    fun delete(sura: Sura)
}
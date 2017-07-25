package com.abdelmeged.ahmed.nourplayerarch.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants
import com.abdelmeged.ahmed.nourplayerarch.utils.QuranIndex
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.io.Serializable

/**
 * Created by ahmed on 7/24/2017.
 */

@Entity(tableName = Constants.TABLE_NAME)
@PaperParcel
data class Sura(
        @PrimaryKey
        val quranIndex: QuranIndex,

        @ColumnInfo(name = "sura_name")
        val suraName: String ,

        @ColumnInfo(name = "download_url")
        var downloadUrl: String
):PaperParcelable{
        companion object {
            @JvmField val CREATOR =  PaperParcelSura.CREATOR
        }
}


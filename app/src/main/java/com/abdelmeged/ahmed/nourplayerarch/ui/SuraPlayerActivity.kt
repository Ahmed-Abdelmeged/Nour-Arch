package com.abdelmeged.ahmed.nourplayerarch.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.abdelmeged.ahmed.nourplayerarch.R
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import com.abdelmeged.ahmed.nourplayerarch.utils.Constants
import kotlinx.android.synthetic.main.activity_sura_player.*
import org.jetbrains.anko.toast


class SuraPlayerActivity : AppCompatActivity() {

    var currentSura: Sura? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura_player)

        currentSura = intent.getParcelableExtra(Constants.EXTRA_SURA)
        if (currentSura == null) {
            toast("Can't play this sura")
            finish()
        }

        sura_name_textView.text = currentSura!!.suraName
    }
}

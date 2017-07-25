package com.abdelmeged.ahmed.nourplayerarch.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import com.abdelmeged.ahmed.nourplayerarch.R
import com.abdelmeged.ahmed.nourplayerarch.db.entity.Sura
import kotlinx.android.synthetic.main.item_sura.view.*
import com.abdelmeged.ahmed.nourplayerarch.utils.FilesUtils


/**
 * Created by ahmed on 7/25/2017.
 */
class SuraRecyclerAdapter(private val context: Context, private val suraClickCallbacks: SuraClickCallbacks,
                          private val suraDownloadClickCallbacks:
                          SuraDownloadClickCallbacks) : RecyclerView.Adapter<SuraRecyclerAdapter.SuraViewHolder>() {

    private var suras: ArrayList<Sura> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SuraViewHolder {
        val context = parent!!.context
        val layoutIdForListItem = R.layout.item_sura
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately)
        return SuraViewHolder(view)
    }

    override fun getItemCount(): Int {
        return suras.size
    }

    override fun onBindViewHolder(holder: SuraViewHolder?, position: Int) {
        val currentSura = suras[position]
        holder!!.suraName.text = currentSura.suraName

        if (FilesUtils.isSuraDownloaded(currentSura.quranIndex, context)) {
            holder.downloadButton.isEnabled = false
            holder.downloadButton.setImageResource(R.drawable.cloud_check)
            holder.suraDownloadingProgressBar.visibility = View.GONE
            holder.downloadButton.visibility = View.VISIBLE
        } else {
            holder.downloadButton.isEnabled = true
            holder.downloadButton.setImageResource(R.drawable.cloud_download)
            holder.suraDownloadingProgressBar.visibility = View.GONE
            holder.downloadButton.visibility = View.VISIBLE
        }

    }


    inner class SuraViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        val suraName: TextView
        val downloadButton: ImageButton
        val suraDownloadingProgressBar: ProgressBar

        constructor(view: View) : super(view) {
            this.suraName = view.sura_name
            this.downloadButton = view.sura_download_button
            this.suraDownloadingProgressBar = view.sura_downloading_progress_bar

            //set the click listener
            downloadButton.setOnClickListener(this)
            view.setOnClickListener(this)
        }


        override fun onClick(view: View) {
            if (view.id == downloadButton.id) {
                suraDownloadClickCallbacks.onSuraDownloadClick(suras[adapterPosition])
                downloadButton.visibility = View.GONE
                suraDownloadingProgressBar.visibility = View.VISIBLE
            } else {
                suraClickCallbacks.onSuraClick(suras[adapterPosition])
            }
        }

    }

    fun setSuras(suras: ArrayList<Sura>) {
        this.suras = suras
        notifyDataSetChanged()
    }

    fun addSura(sura: Sura) {
        this.suras.add(sura)
        notifyDataSetChanged()
    }

    fun clear() {
        this.suras.clear()
        notifyDataSetChanged()
    }
}

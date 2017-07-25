package com.abdelmeged.ahmed.nourplayerarch.utils

import android.content.Context
import java.io.File
import java.io.IOException

/**
 * Created by ahmed on 7/24/2017.
 */
class FilesUtils {
    companion object {

        /**
         * To check if the sura is downloaded or not
         */
        fun isSuraDownloaded(quranIndex: QuranIndex, context: Context): Boolean {
            val audioDownloadDir = File(getAudioDownloadDirectory(context), quranIndex.toString() + ".mp3")
            return audioDownloadDir.canRead()
        }

        /**
         * To get the sura saved Uri
         */
        fun getSuraUri(quranIndex: QuranIndex, context: Context): String {
            val audioDownloadDir = File(getAudioDownloadDirectory(context), quranIndex.toString() + ".mp3")
            return audioDownloadDir.absolutePath
        }

        /**
         * To get the current downloaded directory
         */
        fun getAudioDownloadDirectory(context: Context): File {
            val file = File(context.filesDir, "audio-download")
            if (!file.exists()) {
                val isCreated = file.mkdir()
            }
            return File(context.filesDir, "audio-download")
        }

        /**
         * Helper method to clean the downloaded directory
         */
        @Throws(IOException::class)
        fun cleanAudioDownloadDirectory(context: Context) {
            val audioDownloadDirectory = getAudioDownloadDirectory(context)
            cleanDirectory(audioDownloadDirectory)
        }

        /**
         * To get the current cache directory
         */
        fun getAudioCacheDirectory(context: Context): File {
            return File(context.externalCacheDir, "audio-cache")
        }

        /**
         * Helper method to clean the cache directory
         */
        @Throws(IOException::class)
        fun cleanAudioCacheDirectory(context: Context) {
            val audioCacheDirectory = getAudioCacheDirectory(context)
            cleanDirectory(audioCacheDirectory)
        }

        /**
         * Helper method to clean the directory
         */
        @Throws(IOException::class)
        private fun cleanDirectory(file: File) {
            if (!file.exists()) {
                return
            }
            val contentFiles = file.listFiles()
            for (contentFile in contentFiles) {
                delete(contentFile)
            }
        }

        /**
         * Helper method to delete file and clean the directory
         */
        @Throws(IOException::class)
        private fun delete(file: File) {
            if (file.isFile && file.exists()) {
                deleteOrThrow(file)
            } else {
                cleanDirectory(file)
                deleteOrThrow(file)
            }
        }

        /**
         * Helper method to delete a file or throw IO Exception
         */
        @Throws(IOException::class)
        private fun deleteOrThrow(file: File) {
            if (file.exists()) {
                val isDeleted = file.delete()
                if (!isDeleted) {
                    throw IOException(String.format("File %s can't be deleted", file.absolutePath))
                }
            }
        }
    }
}
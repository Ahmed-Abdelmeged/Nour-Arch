package com.abdelmeged.ahmed.nourplayerarch.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming

/**
 * Created by ahmed on 7/24/2017.
 */
interface NourService {
    @GET("uc?export=download")
    @Streaming
    fun downloadSuraAudio(@Query("id") id: String): Call<ResponseBody>
}
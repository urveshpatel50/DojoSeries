package com.deloitte.series.coroutine.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Factory {

    //https://api.flickr.com/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=96358825614a5d3b1a1c3fd87fca2b47&text=Android%20logo&page=0

    private fun retrofitClient() = Retrofit.Builder()
        .baseUrl("https://api.flickr.com/services/rest/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        ).build()


    fun service() = retrofitClient().create(ApiService::class.java)
}
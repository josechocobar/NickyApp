package com.cuty.nicky.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object RestrofitInstanceCarta {
    const val HEROKU_URL = "https://nicky-api.herokuapp.com/"
    const val URL_BASE = "/api/v1/itemscarta"

    val client : OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(5,TimeUnit.MINUTES)
            .writeTimeout(5,TimeUnit.MINUTES)
            .readTimeout(5,TimeUnit.MINUTES)
            .callTimeout(5,TimeUnit.MINUTES)
            .build()

    val webService : MenuService by lazy {
        Retrofit.Builder()
                .baseUrl(HEROKU_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(MenuService::class.java)
    }
}
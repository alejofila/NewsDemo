package com.element.data.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NewsService{
    val API_URL = "https://docs.google.com"

    fun getNewsApi() : NewsApi{
        val retrofit = Retrofit.Builder().
                baseUrl(API_URL).
                addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(NewsApi::class.java)
    }
}
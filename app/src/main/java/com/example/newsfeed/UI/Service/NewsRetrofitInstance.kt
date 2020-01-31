package com.example.newsfeed.UI.Service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NewsRetrofitInstance {
    private val baseUrl = "https://newsapi.org/"

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val retrofitService = createRetrofit().create<NewsService>()

}
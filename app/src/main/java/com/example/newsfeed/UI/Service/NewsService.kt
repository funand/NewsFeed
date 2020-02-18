package com.example.newsfeed.UI.Service

import com.example.newsfeed.UI.Models.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    fun getNewsArticles(
        @Query("country") country: String,
        @Query("apiKey") apikey: String
    ): Call<NewsModel>

}
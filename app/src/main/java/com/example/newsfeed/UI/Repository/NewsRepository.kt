package com.example.newsfeed.UI.Repository

import android.util.Log
import com.example.newsfeed.UI.Models.NewsModel
import com.example.newsfeed.UI.Service.NewsRetrofitInstance

class NewsRepository(private val newsRetrofitInstance: NewsRetrofitInstance) {

    val country = "us"
    val apiKey = "be0bc3ced8254320aa4cfa7776cee185"

    suspend fun getNewsModel(): NewsModel {
        val newsFeed = newsRetrofitInstance.retrofitService.getNewsArticles(country, apiKey)
        Log.d("repository::", newsFeed.toString())
        return newsFeed
    }

}
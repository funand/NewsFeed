package com.example.newsfeed.UI.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsfeed.UI.Models.NewsModel
import com.example.newsfeed.UI.Service.NewsRetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val newsRetrofitInstance: NewsRetrofitInstance) {

    val country = "us"
    val apiKey = "be0bc3ced8254320aa4cfa7776cee185"

    var mNewsModel = MutableLiveData<NewsModel>()

    fun getNewsModelfromNetwork() {
        val newsFeed = newsRetrofitInstance.retrofitService.getNewsArticles(country, apiKey)

        newsFeed.enqueue(object : Callback<NewsModel> {
            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("Error::", t.message)
            }

            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                Log.d("repositorry", ""+response.body()?.articles?.get(0)?.author)
                response.body()?.let { newsModel -> mNewsModel.postValue(newsModel) }
            }
        })
    }

    fun getNewsModel() = mNewsModel
}
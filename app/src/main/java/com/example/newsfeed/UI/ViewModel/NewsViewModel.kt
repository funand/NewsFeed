package com.example.newsfeed.UI.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsfeed.UI.Models.NewsModel
import com.example.newsfeed.UI.Repository.NewsRepository

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    var newsFeed = MutableLiveData<NewsModel>()

    fun getNewsArticles(): LiveData<NewsModel> {
        newsRepository.getNewsModelfromNetwork()
        newsFeed = newsRepository.getNewsModel()
        Log.d("viemodel", newsFeed.value?.articles?.get(0)?.title ?: "nothing yet")
        return newsFeed
    }

}
package com.example.newsfeed.UI.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeed.UI.Models.NewsModel
import com.example.newsfeed.UI.Repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val newsFeed = MutableLiveData<NewsModel>()

    fun getNewsArticles() = viewModelScope.launch(Dispatchers.IO) {
        newsFeed.postValue(newsRepository.getNewsModel())
        Log.d("viewmodel::", newsFeed.toString())
    }

}
package com.example.newsfeed.UI.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsfeed.UI.Models.NewsModel
import com.example.newsfeed.UI.Service.NewsRetrofitInstance

class NewsRepository(private val newsRetrofitInstance: NewsRetrofitInstance) {

    var mNewsModel = MutableLiveData<NewsModel>()

    fun getNewsModelfromNetwork() {
        newsRetrofitInstance.loadJSON()
        if(newsRetrofitInstance.mCompositeDisposable()){
            Log.d("repository", newsRetrofitInstance.mNewsModel.value?.articles?.get(0)?.author +"  ::")

            mNewsModel.postValue(newsRetrofitInstance.mNewsModel.value)
        }
    }

    fun getNewsModel() = mNewsModel


}
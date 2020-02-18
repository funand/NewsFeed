package com.example.newsfeed.UI.Service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.newsfeed.UI.Models.NewsModel
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsRetrofitInstance {

    private val BASEURL = "https://newsapi.org/"
    private val country = "us"
    private val apiKey = "be0bc3ced8254320aa4cfa7776cee185"

    val mNewsModel = MutableLiveData<NewsModel>()
    fun loadJSON() = Retrofit.Builder()
        .baseUrl(BASEURL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(NewsService::class.java)


    fun mCompositeDisposable() = CompositeDisposable().add(
        loadJSON().getNewsArticles(country, apiKey)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError)
    )

    private fun handleError(throwable: Throwable) {
        Log.d("Error::", throwable.message)
    }

    fun handleResponse(newsModel: NewsModel) {
        Log.d("retrofitinstance", newsModel.articles.get(0).author)
        mNewsModel.postValue(newsModel)
    }
}
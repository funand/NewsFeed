package com.example.newsfeed.UI.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.R
import com.example.newsfeed.UI.Models.Articles
import com.example.newsfeed.UI.Models.NewsModel
import com.example.newsfeed.UI.NewsDataAdapter.DataAdapter
import com.example.newsfeed.UI.Service.NewsService
import com.example.newsfeed.UI.ViewModel.NewsViewModel
import com.example.newsfeed.databinding.ActivityMainBinding
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataSet : List<Articles>
    private lateinit var dataAdapter : DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecycleView()
        loadJSON()
//        initViewModel()
    }

    private fun initViewModel() {
        newsViewModel.getNewsArticles()
        newsViewModel.newsFeed.observe(this@MainActivity, Observer {
            populateUI(it)
        })
    }

    private fun populateUI(newsModel: NewsModel) {
        dataSet = newsModel.articles
        dataAdapter.updateDataSet(dataSet)
    }

    private fun initRecycleView() {
        dataSet = emptyList()
        var recycleview = recycleview
        recycleview.setHasFixedSize(true)
        recycleview.layoutManager = LinearLayoutManager(this)
        dataAdapter = DataAdapter(dataSet)
        recycleview.adapter = dataAdapter
    }

    private val BASEURL = "https://newsapi.org/"
    private val country = "us"
    private val apiKey = "be0bc3ced8254320aa4cfa7776cee185"
    var mCompositeDisposable = CompositeDisposable()


    fun loadJSON() {

        var retrofit = Retrofit.Builder()

            .baseUrl(BASEURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsService::class.java)

        mCompositeDisposable.add(
            retrofit.getNewsArticles(country, apiKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleError(throwable: Throwable) {
        Log.d("Error::", throwable.message)
    }

    fun handleResponse(newsModel: NewsModel) {
        Log.d("retrofitinstance", newsModel.articles.get(0).author)
        dataSet  = newsModel.articles
        dataAdapter.updateDataSet(dataSet)
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.dispose()
    }
}

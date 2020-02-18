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
import com.example.newsfeed.UI.ViewModel.NewsViewModel
import com.example.newsfeed.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        initViewModel()
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
}

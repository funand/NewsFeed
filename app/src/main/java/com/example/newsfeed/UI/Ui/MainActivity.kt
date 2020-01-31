package com.example.newsfeed.UI.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.R
import com.example.newsfeed.UI.NewsDataAdapter.DataAdapter
import com.example.newsfeed.UI.ViewModel.NewsViewModel
import com.example.newsfeed.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecycleView()

        getData()
    }

    private fun getData() {
        newsViewModel.getNewsArticles()
    }

    private fun initRecycleView() {
        var recycleview = recycleview
        recycleview.setHasFixedSize(true)
        recycleview.layoutManager = LinearLayoutManager(this)
        val dataset = newsViewModel.newsFeed.value
        Log.d("dataset::", dataset.toString())
        val dataAdapter = DataAdapter(dataset!!.articles)
        recycleview.adapter = dataAdapter
        newsViewModel.newsFeed.observe(this@MainActivity, Observer { dataAdapter })
    }
}

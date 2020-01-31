package com.example.newsfeed.UI.DependecyInjection

import com.example.newsfeed.UI.Repository.NewsRepository
import com.example.newsfeed.UI.Service.NewsRetrofitInstance
import com.example.newsfeed.UI.ViewModel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val NewsFeedModule = module {
    single { NewsRepository(get()) }
    single { NewsRetrofitInstance() }
    viewModel { NewsViewModel(get()) }
}
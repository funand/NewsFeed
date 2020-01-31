package com.example.newsfeed.UI.DependecyInjection

import android.app.Application
import org.koin.core.context.startKoin

class NewsFeedApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            this@NewsFeedApplication
            modules(NewsFeedModule)
        }
    }
}
package com.alejofila.newsdemo

import android.app.Application
import android.content.Context
import com.alejofila.newsdemo.di.newsModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(newsModule))
    }
}
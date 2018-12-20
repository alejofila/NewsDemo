package com.elements.interviewtest

import android.app.Application
import android.content.Context
import com.elements.interviewtest.di.newsModule
import org.koin.android.ext.android.startKoin

class ElementApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: ElementApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(newsModule))
    }
}
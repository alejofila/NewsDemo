package com.elements.interviewtest

import android.app.Application
import android.content.Context

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


}
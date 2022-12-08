package com.example.longevitytask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App:Application(){


    override fun onCreate() {
        super.onCreate()
        appContext = this

        }

    companion object {

        lateinit var appContext: Application
    }
}
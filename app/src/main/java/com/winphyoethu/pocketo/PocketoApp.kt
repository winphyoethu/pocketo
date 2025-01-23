package com.winphyoethu.pocketo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PocketoApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
package com.alexiscrack3.spinny

import androidx.multidex.MultiDexApplication
import timber.log.Timber

class SpinnyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

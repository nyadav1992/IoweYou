package com.example.ioweyou

import android.app.Application
import com.example.ioweyou.utils.Preferences

class IoweYouApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Preferences.initSharedPreferences(this)
    }
}
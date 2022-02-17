package com.example.ioweyou

import android.app.Application
import com.example.ioweyou.api.ApiInterface
import com.example.ioweyou.api.RetrofitHelper
import com.example.ioweyou.database.IoweYouDatabase
import com.example.ioweyou.utils.Preferences

class IoweYouApplication : Application() {

    lateinit var retrofitInstance: ApiInterface
    lateinit var database: IoweYouDatabase

    override fun onCreate() {
        super.onCreate()
        Preferences.initSharedPreferences(this)
        database = IoweYouDatabase.getDatabase(this)
        retrofitInstance = RetrofitHelper.getRetroFitInstance().create(ApiInterface::class.java)
    }
}
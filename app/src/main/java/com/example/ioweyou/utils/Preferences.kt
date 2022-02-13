package com.example.ioweyou.utils

import android.content.SharedPreferences
import android.app.Activity
import android.content.Context
import java.util.HashSet
import kotlin.jvm.Synchronized

object Preferences {
    private const val PREF_NAME = "IOU"

    private var iouSharedPrefs: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var instance: Preferences? = null

    fun initSharedPreferences(context: Context) {
        instance = Preferences
        iouSharedPrefs = context.getSharedPreferences(
            PREF_NAME, Activity.MODE_PRIVATE
        )
        editor = iouSharedPrefs!!.edit()
    }

    @Synchronized
    fun saveData(key: String?, value: String?): Boolean {
        editor!!.putString(key, value)
        return editor!!.commit()
    }

    @Synchronized
    fun saveData(key: String?, value: Boolean): Boolean {
        editor!!.putBoolean(key, value)
        return editor!!.commit()
    }

    @Synchronized
    fun saveData(key: String?, value: Long): Boolean {
        editor!!.putLong(key, value)
        return editor!!.commit()
    }

    @Synchronized
    fun saveData(key: String?, value: Float): Boolean {
        editor!!.putFloat(key, value)
        return editor!!.commit()
    }

    @Synchronized
    fun saveData(key: String?, value: Int): Boolean {
        editor!!.putInt(key, value)
        return editor!!.commit()
    }

    @Synchronized
    fun saveData(key: String?, value: Set<String?>?): Boolean {
        editor!!.putStringSet(key, value)
        return editor!!.commit()
    }

    val isUserLoggerIn: Boolean
        get() = getData(AppConstants.isUserLoggedIn, false)

    @Synchronized
    fun removeData(key: String?): Boolean {
        editor!!.remove(key)
        return editor!!.commit()
    }

    @Synchronized
    fun getData(key: String?, defaultValue: Boolean): Boolean {
        return iouSharedPrefs!!.getBoolean(key, defaultValue)
    }

    @Synchronized
    fun getData(key: String?, defaultValue: String?): String? {
        return iouSharedPrefs!!.getString(key, defaultValue)
    }

    @Synchronized
    fun getData(key: String?, defaultValue: Float): Float {
        return iouSharedPrefs!!.getFloat(key, defaultValue)
    }

    @Synchronized
    fun getData(key: String?, defaultValue: Int): Int {
        return iouSharedPrefs!!.getInt(key, defaultValue)
    }

    @Synchronized
    fun getData(key: String?, defaultValue: Long): Long {
        return iouSharedPrefs!!.getLong(key, defaultValue)
    }

    @Synchronized
    fun getData(key: String?): Set<String>? {
        return iouSharedPrefs!!.getStringSet(key, HashSet())
    }

    @Synchronized
    fun deleteAllData() {
        instance = null
        editor!!.clear()
        editor!!.commit()
    }
}
package com.example.ioweyou.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ioweyou.utils.MyProgress

open class BaseActivity : AppCompatActivity() {

    fun showProgressDialog() {
        MyProgress.show(this)
    }

    fun showProgressDialogWithMessage(message: String) {
        MyProgress.showWithMessage(this, message)
    }

    fun hideProgressDialog() {
        MyProgress.hide()

    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
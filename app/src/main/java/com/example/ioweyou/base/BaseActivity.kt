package com.example.ioweyou.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ioweyou.interfaces.ICommonViewCallbacks
import com.example.ioweyou.utils.MyProgress


open class BaseActivity : AppCompatActivity(), ICommonViewCallbacks {

    override fun showProgressDialog() {
        MyProgress.show(this)
    }

    override fun showProgressDialogWithMessage(message: String) {
        MyProgress.showWithMessage(this, message)
    }

    override fun hideProgressDialog() {
        MyProgress.hide(this)

    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
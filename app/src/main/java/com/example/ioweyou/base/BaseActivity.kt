package com.example.ioweyou.base

import android.content.Context
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ioweyou.R
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

    fun showAlertSimple(it: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(getString(R.string.app_name))
        alertDialogBuilder.setMessage(it)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}
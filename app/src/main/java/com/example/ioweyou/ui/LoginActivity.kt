package com.example.ioweyou.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ioweyou.R
import com.example.ioweyou.base.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: android.view.View) {
        showProgressDialogWithMessage("Loading...")

        MainScope().launch(Dispatchers.Main) {
            delay(4000)
            hideProgressDialog()
        }
    }
}
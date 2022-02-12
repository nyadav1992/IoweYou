package com.example.ioweyou.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ioweyou.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //added 3 second timer to delay the next screen
        MainScope().launch(Dispatchers.Main) {
                delay(3000)
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            //so that splash can't visible again
            finish()
        }

    }
}
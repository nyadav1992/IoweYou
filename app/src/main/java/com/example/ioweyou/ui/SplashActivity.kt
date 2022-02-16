package com.example.ioweyou.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ioweyou.R
import com.example.ioweyou.utils.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //added 3 second timer to delay the next screen
        MainScope().launch(Dispatchers.Main) {
            delay(1000)
            // Applying condition according to user LogIn status
            if (Preferences.isUserLoggerIn)
                startActivity(Intent(applicationContext, MainActivity::class.java))
            else
                startActivity(Intent(applicationContext, LoginActivity::class.java))

            //finish here so that splash can't visible again on back pressed
            finish()
        }

    }
}
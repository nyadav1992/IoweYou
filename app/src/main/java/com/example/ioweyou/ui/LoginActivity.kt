package com.example.ioweyou.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.R
import com.example.ioweyou.base.BaseActivity
import com.example.ioweyou.models.User
import com.example.ioweyou.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(MainViewModel::class.java)

        viewModel.insertUser(User(1, "Neeraj", "neeraj@gmail.com", "male", 29))
        viewModel.insertUser(User(2, "Neeraj1", "neeraj@gmail.com", "male", 29))
        viewModel.insertUser(User(3, "Neeraj2", "neeraj@gmail.com", "male", 29))
        viewModel.insertUser(User(4, "Neeraj3", "neeraj@gmail.com", "male", 29))

        viewModel.getUser("neeraj@gmail.com").observe(this, Observer {
            Toast.makeText(this, it.eMail.toString(), Toast.LENGTH_LONG).show()
        })

    }

    fun login(view: android.view.View) {

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//        delay(4000)
        showProgressDialogWithMessage("Loading...")
        finish()
        hideProgressDialog()

/*        MainScope().launch(Dispatchers.Main) {
            delay(4000)
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            delay(4000)
            hideProgressDialog()
        }*/
    }
}
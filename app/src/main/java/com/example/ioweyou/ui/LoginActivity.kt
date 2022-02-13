package com.example.ioweyou.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.R
import com.example.ioweyou.base.BaseActivity
import com.example.ioweyou.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_login.*
import android.util.Patterns
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.utils.Preferences


class LoginActivity : BaseActivity() {
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(MainViewModel::class.java)

        //insert user data manually in DB
/*        viewModel.insertUser(User(1, "Neeraj", "neeraj@gmail.com", null, null, "123"))
        viewModel.insertUser(User(2, "Neeraj1", "neeraj@gmail.com", null, null, "123"))
        viewModel.insertUser(User(3, "Neeraj2", "neeraj@gmail.com", "male", 29, "123"))
        viewModel.insertUser(User(4, "Neeraj3", "neeraj@gmail.com", "male", 29, "123"))*/

    }

    fun login(view: android.view.View) {
        //getting value from edit texts
        val enteredValue = etUserName.text.toString().trim()
        val password = etPass.text.toString().trim()

        //Checking validation of input fields
        if (TextUtils.isEmpty(enteredValue)){
            showToast(getString(R.string.enter_email))
        } else if (!isValidEmail(enteredValue)) {
            showToast(getString(R.string.invalid_input))
        } else if (!TextUtils.isEmpty(password)){
            viewModel.getUser(enteredValue).observe(this, Observer {
                if (it != null && it.password.equals(password)) {
                    showToast("Welcome ${it.userName}")
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    Preferences.saveData(AppConstants.isUserLoggedIn, true)
                    Preferences.saveData(AppConstants.USER_EMAIL, it.eMail)
                    finish()
                } else {
                    showToast(getString(R.string.invalid_credentials))
                }
            })
        } else{
            showToast(getString(R.string.enter_password))
        }

    }

    // the function which triggered when the LOGIN button is clicked
    // which validates the email address entered by the user
    fun isValidEmail(enteredValue: String): Boolean {

        // extract the entered data from the EditText


        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        return !enteredValue.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(enteredValue).matches()
    }
}
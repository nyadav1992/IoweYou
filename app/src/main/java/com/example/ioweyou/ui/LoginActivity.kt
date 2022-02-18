package com.example.ioweyou.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.IoweYouApplication
import com.example.ioweyou.R
import com.example.ioweyou.base.BaseActivity
import com.example.ioweyou.repository.ExpenseRepository
import com.example.ioweyou.repository.UserRepository
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.utils.Preferences
import com.example.ioweyou.viewModel.CommonViewModelFactory
import com.example.ioweyou.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userDao = (application as IoweYouApplication).database.getUser()
        val userRepository = UserRepository(userDao)

        //initializing view model
        viewModel = ViewModelProvider(
            this,
            CommonViewModelFactory(userRepository)
        )[UserViewModel::class.java]

        btnSubmit.setOnClickListener { login() }
    }

    private fun login() {
        //getting value from edit texts
        val enteredValue = etUserName.text.toString().trim()
        val password = etPass.text.toString().trim()

        //Checking validation of input fields
        if (TextUtils.isEmpty(enteredValue)) {
            etUserName.error = getString(R.string.mandatory_field)
            showToast(getString(R.string.enter_email))
        } else if (!isValidEmail(enteredValue)) {
            showToast(getString(R.string.invalid_input))
        } else if (!TextUtils.isEmpty(password)) {
            viewModel.getUser(enteredValue).observe(this) {
                if (it != null && it.password == password) {
                    showToast("Welcome ${it.userName}")
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    Preferences.saveData(AppConstants.isUserLoggedIn, true)
                    Preferences.saveData(AppConstants.LOGGED_IN_USER_EMAIL, it.eMail)
                    Preferences.saveData(AppConstants.LOGGED_IN_USER_ID, it.id)
                    Preferences.saveData(AppConstants.LOGGED_IN_USER_NAME, it.userName)
                    finish()
                } else {
                    showToast(getString(R.string.invalid_credentials))
                }
            }
        } else {
            etPass.error = getString(R.string.mandatory_field)
            showToast(getString(R.string.enter_password))
        }

    }

    // the function which triggered when the LOGIN button is clicked
    // which validates the email address entered by the user
    private fun isValidEmail(enteredValue: String): Boolean {

        // extract the entered data from the EditText


        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        return enteredValue.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(enteredValue).matches()
    }
}
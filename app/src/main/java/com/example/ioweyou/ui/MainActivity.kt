package com.example.ioweyou.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.R
import com.example.ioweyou.base.BaseActivity
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.utils.Preferences
import com.example.ioweyou.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var email: String? = null
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setIcon(R.drawable.ic_baseline_person_24)

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(MainViewModel::class.java)
        email = Preferences.getData(AppConstants.USER_EMAIL, "")
        viewModel.getUser(email!!).observe(this,
        Observer {
            it.let {
                when(it.eMail){
                    email -> {
                        supportActionBar?.setTitle(" ${it.userName}")
                    }
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.add_expense -> {
            startActivity(Intent(this, ExpenseDetail::class.java))
            showToast("Add Expenses")
            true
        }
        R.id.home -> {
            showToast("vdcd")
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
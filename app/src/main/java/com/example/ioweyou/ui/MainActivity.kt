package com.example.ioweyou.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ioweyou.R
import com.example.ioweyou.adapters.ExpenseAdapter
import com.example.ioweyou.base.BaseActivity
import com.example.ioweyou.models.User
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.utils.Preferences
import com.example.ioweyou.viewModel.ExpensesViewModel
import com.example.ioweyou.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private var email: String? = null
    lateinit var userViewModel: UserViewModel
    lateinit var expensesViewModel: ExpensesViewModel
    private lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setIcon(R.drawable.ic_baseline_person_24)

        userViewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(UserViewModel::class.java)

        expensesViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(ExpensesViewModel::class.java)

        email = Preferences.getData(AppConstants.USER_EMAIL, "")
        userViewModel.getUser(email!!).observe(this,
        Observer {
            if (it != null) {
                user = it
                supportActionBar?.setTitle(" ${it.userName}")

            }
        })

        val adapter = ExpenseAdapter()

        expensesViewModel.getAllExpenses().observe(this,
            Observer {
                if (it != null) {

                    adapter.submitList(it)

                }
            })

        rvExpenses.layoutManager = LinearLayoutManager(this)
        rvExpenses.adapter = adapter

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
            AddExpenseFragment.newInstance(user.id.toString()).show(supportFragmentManager, AddExpenseFragment.TAG)
            true
        }
        R.id.home -> {
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
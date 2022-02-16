package com.example.ioweyou.ui

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ioweyou.R
import com.example.ioweyou.adapters.ExpenseAdapter
import com.example.ioweyou.adapters.ItemClickListener
import com.example.ioweyou.base.BaseActivity
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.models.User
import com.example.ioweyou.ui.fragments.AddExpenseFragment
import com.example.ioweyou.ui.fragments.ProfileFragment
import com.example.ioweyou.utils.AppConstants
import com.example.ioweyou.utils.Preferences
import com.example.ioweyou.viewModel.ExpensesViewModel
import com.example.ioweyou.viewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ItemClickListener {
    private var email: String? = null
    private lateinit var userViewModel: UserViewModel
    private lateinit var expensesViewModel: ExpensesViewModel
    private lateinit var user: User
    private var userList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setIcon(R.drawable.ic_baseline_person_24)

        //initializing view models
        userViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[UserViewModel::class.java]

        expensesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ExpensesViewModel::class.java]

        //getting logged in user to fetch particular user from table
        email = Preferences.getData(AppConstants.LOGGED_IN_USER_EMAIL, "")

        //getting user by Email from viewModel
        userViewModel.getUser(email!!).observe(
            this
        ) {
            if (it != null) {
                user = it
                supportActionBar?.title = " ${it.userName}"

            }
        }

        /* getting all users from viewModel
         to transfer the list in add expense fragment
         for amount calculation */
        userViewModel.user.observe(
            this
        ) {
            if (it != null) {
                for (i in it)
                    userList.add(i.userName)
            }
        }

        //Method for set up recycler adapter
        setUpRecyclerAdapter()

        my_toolbar.setOnClickListener {
            ProfileFragment.newInstance().show(supportFragmentManager, ProfileFragment.TAG)
        }

    }

    private fun setUpRecyclerAdapter() {
        val adapter = ExpenseAdapter(this)

        expensesViewModel.getAllExpenses().observe(
            this
        ) {
            if (it != null && it.size > 0) {
                adapter.submitList(it.reversed())
                empty_view.visibility = View.GONE
            } else{
                empty_view.visibility = View.VISIBLE
            }
        }

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
            //setting date to blank so that it wont show prefilled after one transaction
            expensesViewModel.setText("")
            AddExpenseFragment.newInstance(user, userList)
                .show(supportFragmentManager, AddExpenseFragment.TAG)
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

    override fun onItemClicked(expenses: Expenses, clickType: String) {
        when (clickType) {
            AppConstants.CLICK_TYPE.DELETE -> {
                askForDeleteExpense(expenses)
                true
            }
            AppConstants.CLICK_TYPE.DETAIL ->{
                startActivity(
                    Intent(
                        this,
                        ExpenseDetail::class.java
                    ).also { it.putExtra(AppConstants.INTENT_KEY_EXTRA, expenses) })
                true
            }
            else -> false
        }
    }

    //method for delete the expense
    private fun askForDeleteExpense(expenses: Expenses) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(this.getString(R.string.delete))
        alertDialogBuilder.setMessage(this.getString(R.string.are_you_sure_to_delete))
        alertDialogBuilder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            expensesViewModel.deleteExpense(expenses)
        }
        alertDialogBuilder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int -> dialogInterface.cancel() }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
}
package com.example.ioweyou.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.ioweyou.R
import com.example.ioweyou.base.BaseActivity
import com.example.ioweyou.databinding.ActivityExpenseDetailBinding
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.utils.AppConstants
import kotlinx.android.synthetic.main.activity_main.*

class ExpenseDetail : BaseActivity() {

    private lateinit var expenseDetailBinding: ActivityExpenseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        expenseDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_expense_detail)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.expense_detail)

        val expenses = intent.getSerializableExtra(AppConstants.INTENT_KEY_EXTRA) as Expenses
        expenseDetailBinding.expenseData = expenses
    }
}
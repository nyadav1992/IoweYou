package com.example.ioweyou.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ioweyou.R
import kotlinx.android.synthetic.main.activity_main.*

class ExpenseDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_detail)
        setSupportActionBar(my_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
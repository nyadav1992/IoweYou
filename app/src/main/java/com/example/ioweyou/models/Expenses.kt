package com.example.ioweyou.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expenses(
    @PrimaryKey(autoGenerate = true)
    val expenseId: Int,
    val title: String,
    val date: String,
    val totalAmount: String,
    val description: String? = null,
    val paidBy: String
    )

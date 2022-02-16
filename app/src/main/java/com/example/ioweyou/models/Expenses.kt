package com.example.ioweyou.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "expenses")
data class Expenses(
    @PrimaryKey(autoGenerate = true)
    val expenseId: Int,
    val title: String,
    val date: String,
    val totalAmount: String,
    val description: String? = null,
    val paidBy: String,
    var paidByName: String? = null,
    val youOwe: String? = null,
    val youGetBack: String? = null,
    var splitWith: List<String>? = null,
    var isByYou: Boolean? = null
) : Serializable

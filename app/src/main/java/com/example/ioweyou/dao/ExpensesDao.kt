package com.example.ioweyou.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ioweyou.models.Expenses

@Dao
interface ExpensesDao {

    @Insert
    suspend fun insertExpense(expenses: Expenses)

    @Update
    suspend fun updateExpense(expenses: Expenses)

    @Delete
    suspend fun deleteExpense(expenses: Expenses)

    @Query("Select * From expenses")
    fun getAllExpenses() : LiveData<List<Expenses>>

    @Query("Select * From expenses where expenseId=:id")
    fun getExpensebyid(id: Int) : LiveData<Expenses>

}
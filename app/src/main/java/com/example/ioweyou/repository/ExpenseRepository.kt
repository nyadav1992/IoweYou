package com.example.ioweyou.repository

import androidx.lifecycle.LiveData
import com.example.ioweyou.dao.ExpensesDao
import com.example.ioweyou.models.Expenses

class ExpenseRepository(private val expensesDao: ExpensesDao) {

    fun getAllExpenses(): LiveData<List<Expenses>> = expensesDao.getAllExpenses()

    fun getExpenseById(id: Int): LiveData<Expenses> = expensesDao.getExpensebyid(id)

    suspend fun insertExpense(expenses: Expenses) {
        expensesDao.insertExpense(expenses)
    }

    suspend fun deleteExpense(expenses: Expenses) {
        expensesDao.deleteExpense(expenses)
    }

}
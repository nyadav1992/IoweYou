package com.example.ioweyou.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ioweyou.database.IoweYouDatabase
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesViewModel(application: Application): AndroidViewModel(application) {
    private var expenseRepository: ExpenseRepository

    init {
        val expensesDao = IoweYouDatabase.getDatabase(application).getExpenses()
        expenseRepository = ExpenseRepository(expensesDao)
    }

    fun getExpenseById(id: Int) = expenseRepository.getExpenseById(id)

    fun getAllExpenses() = expenseRepository.getAllExpenses()

    fun insertExpense(expenses: Expenses) = viewModelScope.launch(Dispatchers.IO) {
        expenseRepository.insertExpense(expenses)
    }

    //live data and methods to save selected date when scree rotate.
    private var _text = MutableLiveData<String>().apply {
        value = ""
    }
    var text: LiveData<String> = _text

    fun setText(text: String){
        _text.postValue(text)
    }

}
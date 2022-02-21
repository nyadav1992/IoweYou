package com.example.ioweyou.viewModel

import androidx.lifecycle.*
import com.example.ioweyou.models.Expenses
import com.example.ioweyou.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpensesViewModel(private val expenseRepository: ExpenseRepository) : ViewModel() {

    fun getExpenseById(id: Int) = expenseRepository.getExpenseById(id)

    fun getAllExpenses() = expenseRepository.getAllExpenses()

    fun insertExpense(expenses: Expenses) = viewModelScope.launch(Dispatchers.IO) {
        expenseRepository.insertExpense(expenses)
    }

    fun deleteExpense(expenses: Expenses) = viewModelScope.launch(Dispatchers.IO) {
        expenseRepository.deleteExpense(expenses)
    }

    //live data and methods to save selected date when scree rotate.
    private var date = MutableLiveData<String>().apply {
        value = ""
    }
    var dateString: LiveData<String> = date

    fun setText(text: String) {
        date.postValue(text)
    }

}
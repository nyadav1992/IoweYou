package com.example.ioweyou.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ioweyou.repository.ExpenseRepository
import com.example.ioweyou.repository.UserRepository

class CommonViewModelFactory<T>(private val repository: T) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelrajuClass: Class<T>): T {
        return when (repository) {
            is ExpenseRepository -> ExpensesViewModel(repository as ExpenseRepository) as T
            is UserRepository -> UserViewModel(repository as UserRepository) as T
            else -> repository as T
        }
    }
}
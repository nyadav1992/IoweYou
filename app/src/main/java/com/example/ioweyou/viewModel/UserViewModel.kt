package com.example.ioweyou.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioweyou.models.User
import com.example.ioweyou.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private var userLiveData = MutableLiveData<List<User>>()

    val user: LiveData<List<User>>
        get() = repository.getUser()


    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(user)
    }

    fun getUser(email: String) = repository.getUserById(email)
}
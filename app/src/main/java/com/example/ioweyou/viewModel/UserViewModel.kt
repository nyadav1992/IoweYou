package com.example.ioweyou.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ioweyou.database.IoweYouDatabase
import com.example.ioweyou.models.User
import com.example.ioweyou.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    var user: LiveData<List<User>>
//    private var repository: UserRepository


    val userData: LiveData<List<User>>
        get() {
            return repository.getUser()
        }

    init {
/*        val userDao = IoweYouDatabase.getDatabase(application).getUser()
        repository = UserRepository(userDao)*/
        user = repository.getUser()
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(user)
    }

    fun getUser(email: String) = repository.getUserById(email)
}
package com.example.ioweyou.repository

import androidx.lifecycle.LiveData
import com.example.ioweyou.dao.UserDao
import com.example.ioweyou.models.User

class UserRepository(private val userDao: UserDao) {

    //getting all user
    fun getUser(): LiveData<List<User>> = userDao.getAllUser()

    //inserting user in DB
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    //getting user by email
    fun getUserById(email: String): LiveData<User> = userDao.getUserByEmail(email)
}
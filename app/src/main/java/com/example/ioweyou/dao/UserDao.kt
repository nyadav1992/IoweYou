package com.example.ioweyou.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ioweyou.models.User

@Dao
interface UserDao {
    //to insert in user table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    //to update in user table
    @Update
    suspend fun updateUser(user: User)

    //to delete from user table
    @Delete
    suspend fun deleteUser(user: User)

    //to get userlist
    @Query("SELECT * FROM user_table")
    fun getAllUser(): LiveData<List<User>>

    //to get user by id
    @Query("SELECT * FROM user_table where eMail=:email")
    fun getUserByEmail(email: String): LiveData<User>

}
package com.example.ioweyou.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ioweyou.dao.UserDao
import com.example.ioweyou.models.User

@Database(entities = [User::class], version = 1)
abstract class IoweYouDatabase : RoomDatabase() {

    abstract fun getUser(): UserDao

    companion object{
        private var INSTANCE: IoweYouDatabase? = null

        fun getDatabase(context: Context): IoweYouDatabase{
            return INSTANCE?: synchronized(this){
                return Room.databaseBuilder(context.applicationContext,
                IoweYouDatabase::class.java,
                "IoweYouDB").build()
            }
        }
    }
}
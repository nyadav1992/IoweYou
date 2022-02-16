package com.example.ioweyou.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


// making eMail as unique key so that duplicate value cant be inserted
@Entity(tableName = "user_table", indices = [Index(value = ["eMail"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val eMail: String,
    val gender: String? = null,
    val age: Int? = null,
    val password: String,
) : Serializable

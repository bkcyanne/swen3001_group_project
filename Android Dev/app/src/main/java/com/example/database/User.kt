package com.example.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="UserInfo")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstName:String,
    val lastName: String,
    val email:String,
    val password: String

)
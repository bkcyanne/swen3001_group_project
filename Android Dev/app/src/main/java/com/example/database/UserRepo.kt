package com.example.database

import androidx.lifecycle.LiveData

class UserRepo(private val userDataAccessObject: UserDao) {

    val readAllUserData: LiveData<List<User>> = userDataAccessObject.readAllUserData()
    var thisIntegerSumn: Int = 1

    suspend fun  addUser(user: User){

        userDataAccessObject.addUser(user)
    }
    fun exists(inputUser:String){


        thisIntegerSumn=userDataAccessObject.exists(inputUser)
    }
}
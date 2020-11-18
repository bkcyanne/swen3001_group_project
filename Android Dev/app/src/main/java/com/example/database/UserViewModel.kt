package com.example.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

    private var readAllData: LiveData<List<User>>
    private var repo:UserRepo

    init{

        val userDao = UserDatabase.getDatabase(application).userDao()
        repo= UserRepo(userDao)
        readAllData= repo.readAllUserData
    }

    fun addUser(user:User){

        viewModelScope.launch(Dispatchers.IO){

            repo.addUser(user)

        }
    }



}
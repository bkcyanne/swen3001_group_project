package com.example.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Query( "SELECT* FROM UserInfo ORDER BY id  ASC" )
    fun readAllUserData(): LiveData<List<User>>

    @Query(" SELECT COUNT(email) from UserInfo WHERE email= :email")
    fun exists(email:String):Int

}

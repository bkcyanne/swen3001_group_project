package com.example.taskdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)
}
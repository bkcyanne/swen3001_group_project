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

    @Delete
    suspend fun deleteTask(task: Task)

    /*
    Methods to set tasks as incomplete or complete
     */
    @Query("UPDATE task_table SET status = 1 WHERE id = :id")
    suspend fun taskIncomplete(id: Int)

    @Query("UPDATE task_table SET status = 0 WHERE id = :id")
    suspend fun taskComplete(id: Int)

    /*
    Return list of active tasks
     */
    @Query("SELECT * FROM task_table WHERE status = 0")
    fun showActiveTask(): LiveData<List<Task>>

    /*
    Return count of active tasks
     */
    @Query("SELECT COUNT(*) FROM task_table WHERE status = 0")
    fun activeTaskCount(): Int

    /*
    Return total number of tasks
     */
    @Query("SELECT COUNT(*) FROM task_table")
    fun allTaskCount(): Int
}

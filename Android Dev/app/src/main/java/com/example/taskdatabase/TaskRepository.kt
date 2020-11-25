package com.example.taskdatabase

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.readAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun incompleteTask(id: Int){
        taskDao.taskIncomplete(id)
    }

    suspend fun completeTask(id: Int){
        taskDao.taskComplete(id)
    }
}
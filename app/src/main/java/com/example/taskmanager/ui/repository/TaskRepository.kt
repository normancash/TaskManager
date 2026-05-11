package com.example.taskmanager.ui.repository

import com.example.taskmanager.ui.dao.TaskDao
import com.example.taskmanager.ui.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getTask() = taskDao.getAllTasks()

    suspend fun getTaskById(taskId: Int) = taskDao.getTaskById(taskId)

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

}
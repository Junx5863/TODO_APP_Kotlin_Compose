package com.example.todo_app_curso_platzi.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: String): TaskEntity?

    @Upsert //Update o insert Task
    suspend fun  upsertTask(task: TaskEntity)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deletedTaskById(id: String)

    @Query("DELETE FROM task")
    suspend fun deletedAllTasks()
}
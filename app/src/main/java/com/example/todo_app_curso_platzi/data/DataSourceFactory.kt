package com.example.todo_app_curso_platzi.data

import android.content.Context
import com.example.todo_app_curso_platzi.domain.TaskLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher

object DataSourceFactory {
    fun createDateSource(
        context: Context,
        dispatcherIo: CoroutineDispatcher,
        ):TaskLocalDataSource{
        val database = TodoDataBase.getDatabase(context)
        return RoomTaskLocalDataSource(database.taskDao(),dispatcherIo)
    }
}
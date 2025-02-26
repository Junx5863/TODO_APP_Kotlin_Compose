package com.example.todo_app_curso_platzi

import android.app.Application
import com.example.todo_app_curso_platzi.data.DataSourceFactory
import com.example.todo_app_curso_platzi.domain.TaskLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TodoApplication:Application() {
    val dispatcherIO: CoroutineDispatcher
        get() = Dispatchers.IO

    val dataSource: TaskLocalDataSource
        get() = DataSourceFactory.createDateSource(this, dispatcherIO)

}
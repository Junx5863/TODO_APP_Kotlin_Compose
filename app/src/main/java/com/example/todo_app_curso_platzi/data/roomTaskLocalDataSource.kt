package com.example.todo_app_curso_platzi.data

import com.example.todo_app_curso_platzi.domain.Task
import com.example.todo_app_curso_platzi.domain.TaskLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomTaskLocalDataSource(
    private val taskDao: TaskDao,
    private val dispatcherIo: CoroutineDispatcher = Dispatchers.IO
) : TaskLocalDataSource {
    override val taskFlow: Flow<List<Task>>
        get() = taskDao.getAllTasks().map {
            it.map { taskEntity -> taskEntity.toTask() }
        }.flowOn(dispatcherIo)

    override suspend fun addTask(task: Task) = withContext(dispatcherIo) {
        taskDao.upsertTask(TaskEntity.fromTask(task))
    }

    override suspend fun updateTask(task: Task) = withContext(dispatcherIo) {
        taskDao.upsertTask(TaskEntity.fromTask(task))
    }

    override suspend fun removeTask(task: Task) = withContext(dispatcherIo) {
        taskDao.deletedTaskById(task.id)
    }

    override suspend fun deleteAllTasks() = withContext(dispatcherIo) {
        taskDao.deletedAllTasks()
    }

    override suspend fun getTasksById(id: String): Task? = withContext(dispatcherIo) {
        taskDao.getTaskById(id)?.toTask()

    }
}
package com.example.todo_app_curso_platzi.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_curso_platzi.data.FakeTaskLocalDataSource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val taskLocalDataSource = FakeTaskLocalDataSource

    var state by mutableStateOf(HomeDataState())
        private set

    private val eventChannel = Channel<HomeScreenEvent>()
    val event = eventChannel.receiveAsFlow()

    init {
        taskLocalDataSource.taskFlow
            .onEach {
                val completeTask = it.filter { task -> task.isImportant }
                val pendingTask = it.filter { task -> !task.isImportant }

                state = state.copy(
                    date = "Marzo 9, 2025",
                    summary = "${pendingTask.size} incomplete, ${pendingTask.size} Completed",
                    completeTask = completeTask,
                    pendingTask = pendingTask,
                )
            }
            .launchIn(viewModelScope)
    }

    fun onAction(action:HomeScreenAction){
        viewModelScope.launch {
            when(action){
                HomeScreenAction.OnDeleteAllTasks -> {
                    taskLocalDataSource.deleteAllTasks()
                    eventChannel.send(HomeScreenEvent.UpdateDataTask)
                }
                is HomeScreenAction.OnDeleteTask -> {
                    taskLocalDataSource.removeTask(action.task)
                    eventChannel.send(HomeScreenEvent.OnDeleteTask)
                }
                is HomeScreenAction.OnToggleTask -> {
                    val updateTask = action.task.copy(isImportant = !action.task.isImportant)
                    taskLocalDataSource.updateTask(updateTask)
                    eventChannel.send(HomeScreenEvent.UpdateDataTask)
                }
                else -> {}
            }
        }
    }


}
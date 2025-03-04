package com.example.todo_app_curso_platzi.presentation.detail
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app_curso_platzi.domain.Task
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID

class TaskViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val fakeTaskLocalDataSource =
        com.example.todo_app_curso_platzi.data.FakeTaskLocalDataSource




    var state by mutableStateOf(TaskScreenState())
        private set

    private val eventChannel = Channel<TaskEvent>()
    val events = eventChannel.receiveAsFlow()
    private val canSaveTask = snapshotFlow { state.taskName.text.toString() }
    val idTask: String = savedStateHandle.get<String>("idTask") ?: ""

    private var editedTask: Task? = null

    init {
        idTask.let {
            viewModelScope.launch {
                fakeTaskLocalDataSource.getTasksById(idTask)?.let {
                        taskInfo ->
                    editedTask = taskInfo
                    state = state.copy(
                        taskName = TextFieldState(taskInfo.title),
                        taskDescription = TextFieldState(taskInfo.description ?: ""),
                        isTaskDone = taskInfo.isImportant,
                        category = taskInfo.category
                    )
                }
            }
        }

        canSaveTask.onEach {
            state = state.copy( canSaveTask = it.isNotEmpty())
        }.launchIn(viewModelScope)
    }

    fun onAction(actionTask: ActionTask) {
        viewModelScope.launch {
            when (actionTask) {

                is ActionTask.ChangeTaskCategory -> {
                    state = state.copy(category = actionTask.category)
                }

                is ActionTask.ChangeTaskDone -> {
                    state = state.copy(
                        isTaskDone = actionTask.isTaskDone
                    )
                }

                is ActionTask.SaveTask -> {
                    val task = Task(
                        id = UUID.randomUUID().toString(),
                        title = state.taskName.text.toString(),
                        description = state.taskDescription.text.toString(),
                        category = state.category,

                        )
                    fakeTaskLocalDataSource.addTask(
                        task = task
                    )
                    eventChannel.send(TaskEvent.TaskCreate)
                }
                else -> Unit
            }
        }
    }

}
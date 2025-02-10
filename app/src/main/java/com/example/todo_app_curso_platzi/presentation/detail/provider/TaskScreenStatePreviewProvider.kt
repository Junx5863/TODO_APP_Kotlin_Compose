package com.example.todo_app_curso_platzi.presentation.detail.provider


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.todo_app_curso_platzi.domain.Category.WORK
import com.example.todo_app_curso_platzi.domain.Category.OTHER
import com.example.todo_app_curso_platzi.presentation.detail.TaskScreenState


class TaskScreenStatePreviewProvider: PreviewParameterProvider<TaskScreenState> {
    @OptIn(ExperimentalFoundationApi::class)
    override val values: Sequence<TaskScreenState>
        get() = sequenceOf(
            TaskScreenState(
                taskName = TextFieldState("Task 1"),
                taskDescription = TextFieldState("Description 1"),
                isTaskDone = false,
                category = WORK
            ),
            TaskScreenState(
                taskName = TextFieldState("Task 2"),
                taskDescription = TextFieldState("Description 2"),
                isTaskDone = true,
                category = WORK
            ),
            TaskScreenState(
                taskName = TextFieldState("Task 3"),
                taskDescription = TextFieldState("Description 3"),
                isTaskDone = false,
                category = OTHER
            ),
            TaskScreenState(
                taskName = TextFieldState("Task 3"),
                taskDescription = TextFieldState("Description 3"),
                isTaskDone = true,
                category = null
            ),
            TaskScreenState(
                taskName = TextFieldState("Task 4"),
                taskDescription = TextFieldState(""),
                isTaskDone = false,
                category = null
            )
        )
}
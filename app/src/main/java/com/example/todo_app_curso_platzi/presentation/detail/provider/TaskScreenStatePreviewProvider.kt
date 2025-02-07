package com.example.todo_app_curso_platzi.presentation.detail.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.todo_app_curso_platzi.domain.Category
import com.example.todo_app_curso_platzi.presentation.detail.TaskScreenState


class TaskScreenStatePreviewProvider: PreviewParameterProvider<TaskScreenState> {
    override val values: Sequence<TaskScreenState>
        get() = sequenceOf(
            TaskScreenState(
                taskName = "Task 1",
                taskDescription = "Description 1",
                isTaskDone = false,
                category = Category.WORK
            ),
            TaskScreenState(
                taskName = "Task 2",
                taskDescription = "Description 2",
                isTaskDone = true,
                category = Category.WORK
            ),
            TaskScreenState(
                taskName = "Task 3",
                taskDescription = "Description 3",
                isTaskDone = false,
                category = Category.OTHER
            ),
            TaskScreenState(
                taskName = "Task 4",
                taskDescription = "Description 4",
                isTaskDone = true,
                category = null
            ),
            TaskScreenState(
                taskName = "Task 5",
                taskDescription = null,
                isTaskDone = false,
                category = null
            )
        )
}
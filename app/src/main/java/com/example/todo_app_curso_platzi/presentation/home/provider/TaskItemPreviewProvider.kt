package com.example.todo_app_curso_platzi.presentation.home.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.todo_app_curso_platzi.domain.Category
import com.example.todo_app_curso_platzi.domain.Task

class TaskItemPreviewProvider: PreviewParameterProvider<Task> {
    override val values: Sequence<Task>
        get() = sequenceOf(
            Task(
                id = "1",
                title = "Task 1",
                isImportant = false,
                description = "Description 1",
                category = Category.WORK,
            ),

            Task(
                id = "2",
                title = "Task 2",
                isImportant = true,
                description = "Description 2",
                category = Category.OTHER,
            ),
        )
}
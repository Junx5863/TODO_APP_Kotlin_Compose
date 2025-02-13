package com.example.todo_app_curso_platzi.presentation.detail



import androidx.compose.foundation.text.input.TextFieldState
import com.example.todo_app_curso_platzi.domain.Category

data class TaskScreenState(
    val taskName: TextFieldState = TextFieldState(),
    val taskDescription: TextFieldState = TextFieldState(),
    val category: Category? = null,
    val isTaskDone: Boolean = false,
    val canSaveTask: Boolean = false
)
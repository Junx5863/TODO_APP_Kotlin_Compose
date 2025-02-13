package com.example.todo_app_curso_platzi.presentation.detail

sealed class TaskEvent {
    data object TaskCreate: TaskEvent()
}
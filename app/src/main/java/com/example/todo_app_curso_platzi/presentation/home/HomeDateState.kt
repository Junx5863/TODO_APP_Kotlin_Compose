package com.example.todo_app_curso_platzi.presentation.home

import com.example.todo_app_curso_platzi.domain.Task


data class HomeDataState(
    val date: String = "",
    val summary: String = "",
    val completeTask: List<Task> = emptyList(),
    val pendingTask: List<Task> = emptyList(),
)

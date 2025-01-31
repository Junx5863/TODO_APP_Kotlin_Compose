package com.example.todo_app_curso_platzi.presentation.home

sealed class HomeScreenEvent {
    data object OnDeleteAllTasks:HomeScreenEvent()
    data object OnDeleteTask:HomeScreenEvent()
    data object UpdateDataTask:HomeScreenEvent()
}
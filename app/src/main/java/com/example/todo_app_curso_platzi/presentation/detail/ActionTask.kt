package com.example.todo_app_curso_platzi.presentation.detail

import com.example.todo_app_curso_platzi.domain.Category

sealed class ActionTask {
    data object SaveTask: ActionTask()
    data object Back: ActionTask()
    data class ChangeTaskCategory(val category: Category?): ActionTask()
    data class ChangeTaskDone(val isTaskDone: Boolean): ActionTask()

}
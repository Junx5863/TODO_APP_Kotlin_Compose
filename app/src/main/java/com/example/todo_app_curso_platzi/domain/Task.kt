package com.example.todo_app_curso_platzi.domain

import java.time.LocalDateTime

data class Task(
    val id:String,
    val title:String,
    val description:String?,
    val isImportant:Boolean = false,
    val category: Category? = null,
    val date: LocalDateTime? = LocalDateTime.now(),
)
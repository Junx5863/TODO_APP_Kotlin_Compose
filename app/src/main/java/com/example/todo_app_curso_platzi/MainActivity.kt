package com.example.todo_app_curso_platzi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todo_app_curso_platzi.presentation.detail.TaskScreenRoot
import com.example.todo_app_curso_platzi.ui.theme.TODO_APP_Curso_PlatziTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODO_APP_Curso_PlatziTheme() {
                TaskScreenRoot()
            }
        }
    }
}

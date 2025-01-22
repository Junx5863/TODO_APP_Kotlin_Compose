package com.example.todo_app_curso_platzi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.todo_app_curso_platzi.data.FakeTaskLocalDataSource
import com.example.todo_app_curso_platzi.domain.Task
import com.example.todo_app_curso_platzi.ui.theme.TODO_APP_Curso_PlatziTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODO_APP_Curso_PlatziTheme {
                val fakeLocalDataSource = FakeTaskLocalDataSource

                var text by remember { mutableStateOf("") }
                LaunchedEffect(true) {
                    fakeLocalDataSource.taskFlow.collect{
                        text = it.toString()
                    }
                }
                LaunchedEffect(true) {
                    fakeLocalDataSource.addTask(
                        Task(
                            id = UUID.randomUUID().toString(),
                            title = "Task 1",
                            description = "Description 1",
                            isImportant = true
                        )
                    )
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = text,
                        modifier = Modifier
                            .padding(top =  innerPadding.calculateTopPadding())
                            .fillMaxSize()
                    )

                }
            }
        }
    }
}

package com.example.todo_app_curso_platzi.core.navigation



sealed class Routes(val routes:String){
    data object HomeScreen: Routes("homeScreen")
    data object TaskBottomBar: Routes("taskBottomBar/{idTask}"){
        fun createRoute(idTask:String) = "taskBottomBar/$idTask"
    }
}
@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.todo_app_curso_platzi.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todo_app_curso_platzi.presentation.detail.BottomTaskBarRoot
import com.example.todo_app_curso_platzi.presentation.detail.TaskViewModel
import com.example.todo_app_curso_platzi.presentation.home.HomeScreenRoot
import com.example.todo_app_curso_platzi.presentation.home.HomeScreenViewModel


@Composable
fun NavigatorRoot(
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.HomeScreen.routes
        ) {
            composable(Routes.HomeScreen.routes) {
                val viewModel: HomeScreenViewModel = viewModel<HomeScreenViewModel>(
                    factory = HomeScreenViewModel.Factory
                )
                HomeScreenRoot(
                    navController,
                    viewModel = viewModel,
                )
            }
            composable(
                Routes.TaskBottomBar.routes,
                arguments = listOf(navArgument("idTask") { type = NavType.StringType })
            ) {

                    navBackStackEntry ->
                val idTask = navBackStackEntry.arguments?.getString("idTask") ?: ""
                val viewmodel = viewModel<TaskViewModel>(
                    factory = TaskViewModel.Factory
                )
                BottomTaskBarRoot(navController, idTask, viewmodel)
            }
        }
    }
}

@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.example.todo_app_curso_platzi.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo_app_curso_platzi.presentation.detail.BottomTaskBarRoot
import com.example.todo_app_curso_platzi.presentation.home.HomeScreenRoot


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
                HomeScreenRoot(navController)
            }
            composable(
                Routes.TaskBottomBar.routes,
            ) { navBackStackEntry ->
                BottomTaskBarRoot(
                    navController,
                    navBackStackEntry.arguments?.getString("idTask") ?: ""
                )

            }
        }
    }
}

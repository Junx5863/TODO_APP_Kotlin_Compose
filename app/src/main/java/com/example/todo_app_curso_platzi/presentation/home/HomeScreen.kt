@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package com.example.todo_app_curso_platzi.presentation.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todo_app_curso_platzi.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todo_app_curso_platzi.core.navigation.Routes
import com.example.todo_app_curso_platzi.presentation.home.components.SectionTitle
import com.example.todo_app_curso_platzi.presentation.home.components.SummaryInfo
import com.example.todo_app_curso_platzi.presentation.home.components.TaskItem

@Composable
fun HomeScreenRoot(
    navController: NavController
) {
    val viewModel: HomeScreenViewModel = viewModel<HomeScreenViewModel>()
    val state = viewModel.state
    val event = viewModel.event


    val context = LocalContext.current

    LaunchedEffect(true) {
        event.collect { event ->
            when (event) {
                HomeScreenEvent.OnDeleteAllTasks -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.all_tasks_deleted),
                        Toast.LENGTH_SHORT,
                    ).show()
                }

                HomeScreenEvent.OnDeleteTask -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.task_deleted),
                        Toast.LENGTH_SHORT,
                    ).show()
                }

                HomeScreenEvent.UpdateDataTask -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.update_task),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }
    }

    HomeScreen(
        state = state,
        onAction = {
            action ->
            when(action){
                HomeScreenAction.OnAddTask -> {
                    navController.navigate(Routes.TaskBottomBar.routes)
                }
                is HomeScreenAction.OnClickTask -> {
                    navController.navigate(Routes.TaskBottomBar.createRoute(action.taskId))
                }
                else -> {
                    viewModel.onAction(action)
                }
            }
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeDataState,
    onAction: (HomeScreenAction) -> Unit
) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        modifier = Modifier.fillMaxSize()
                    )
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                isMenuExpanded = true
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Add Task",
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                        DropdownMenu(
                            expanded = isMenuExpanded,
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.surfaceContainerHighest
                            ),
                            onDismissRequest = { isMenuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    onAction(HomeScreenAction.OnDeleteAllTasks)
                                    isMenuExpanded = true
                                },
                                text = {
                                    Text(
                                        text = stringResource(id = R.string.deleteAll),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                },

                                )

                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                     onAction(HomeScreenAction.OnAddTask)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                SummaryInfo(
                    date = state.date,
                    taskSummary = stringResource(R.string.summary_info, state.summary),
                    completeTask = state.completeTask.size,
                    totalTask = state.completeTask.size + state.pendingTask.size
                )
            }

            stickyHeader {
                SectionTitle(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface
                        ),
                    title = stringResource(R.string.complete_task)
                )
            }

            items(
                state.completeTask,
                key = { task -> task.id }
            ) { task ->
                TaskItem(
                    modifier = Modifier.clip(
                        RoundedCornerShape(8.dp)
                    ),
                    task = task,
                    onClickItem = {
                        onAction(HomeScreenAction.OnClickTask(task.id))
                    },
                    onDeleteItem = {
                        onAction(HomeScreenAction.OnDeleteTask(task))
                    },
                    onToggleCompletion = {
                        onAction(HomeScreenAction.OnToggleTask(task))
                    }

                )
            }

            stickyHeader {
                SectionTitle(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surface
                        ),
                    title = stringResource(R.string.pending_task)
                )
            }

            items(
                state.pendingTask,
                key = { task -> task.id }
            ) { task ->
                TaskItem(
                    modifier = Modifier.clip(
                        RoundedCornerShape(
                            8.dp
                        )
                    ),
                    task = task,
                    onClickItem = {
                        onAction(HomeScreenAction.OnClickTask(task.id))
                        println("Task ID: ${task.id}")
                    },
                    onDeleteItem = {
                        onAction(HomeScreenAction.OnDeleteAllTasks)
                    },
                    onToggleCompletion = {
                        onAction(HomeScreenAction.OnToggleTask(task))
                    }
                )
            }
        }

    }

}


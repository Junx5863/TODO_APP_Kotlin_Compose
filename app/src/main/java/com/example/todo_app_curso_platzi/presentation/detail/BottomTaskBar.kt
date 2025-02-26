@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todo_app_curso_platzi.presentation.detail


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo_app_curso_platzi.R
import com.example.todo_app_curso_platzi.core.navigation.Routes
import com.example.todo_app_curso_platzi.domain.Category



@Composable
fun BottomTaskBarRoot(
    navController: NavController,
    idTask: String,
    viewModel: TaskViewModel,
) {

    val state = viewModel.state
    val event = viewModel.events

    val context = LocalContext.current

    LaunchedEffect(true) {
        event.collect { event ->
            when (event) {
                TaskEvent.TaskCreate -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.task_save),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    BottomTaskBar(
        state = state,
        onActionTask = { action ->
            when (action) {
                is ActionTask.Back -> {
                    navController.navigate(
                        Routes.HomeScreen.routes
                    )
                }
                else -> viewModel.onAction(action)
            }
        },
        idTask = idTask
    )
}

@Composable
fun BottomTaskBar(
    modifier: Modifier = Modifier,
    state: TaskScreenState,
    onActionTask: (ActionTask) -> Unit,
    idTask: String
) {

    var isExpanded by remember { mutableStateOf(false) }
    var isDescriptionFocused by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {
            onActionTask(
                ActionTask.Back
            )
        },
        sheetState = sheetState
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),

            ) {
            IconButton(
                onClick = {
                    onActionTask(
                        ActionTask.Back
                    )
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar")
            }

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.done), modifier = Modifier.padding(7.dp)
                )
                Checkbox(
                    checked = state.isTaskDone,
                    onCheckedChange = {
                        onActionTask(
                            ActionTask.ChangeTaskDone(
                                isTaskDone = it
                            )
                        )
                    })
                Spacer(
                    modifier = modifier.weight(1f)
                )
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isExpanded = true
                    }) {
                    Text(
                        text = state.category?.toString()
                            ?: stringResource(R.string.category),
                        style = typography.bodyMedium.copy(
                            color = colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = colorScheme.outline,
                                shape = RoundedCornerShape(
                                    8.dp
                                )
                            )
                            .padding(
                                8.dp
                            )
                    )
                    Box(
                        modifier = Modifier.padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        DropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false },
                            modifier = Modifier.background(
                                color = colorScheme.surfaceContainerHighest
                            )
                        ) {
                            Category.entries.forEach { category ->
                                Text(text = category.name,
                                    style = typography.bodyMedium.copy(
                                        color = colorScheme.onSurface
                                    ),
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .clickable {
                                            onActionTask(
                                                ActionTask.ChangeTaskCategory(
                                                    category = category
                                                )
                                            )
                                            isExpanded = false
                                        }
                                )
                            }

                        }
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = colorScheme.onSurface,
                        )
                    }
                }
            }
            BasicTextField(
                state = state.taskName,
                textStyle = typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onSurface,
                ),
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                decorator = { innerBox ->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (state.taskName.text.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.task_name),
                                color = colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                ),
                                style = typography.headlineLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        } else {
                            innerBox()
                        }
                    }
                },
            )

            BasicTextField(
                state = state.taskDescription,
                textStyle = typography.bodyLarge.copy(
                    color = colorScheme.onSurface,
                ),


                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isDescriptionFocused = it.isFocused
                    },
                decorator = { innerBox ->
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (state.taskDescription.text.isEmpty() && !isDescriptionFocused) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.task_description),
                                color = colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                ),
                                style = typography.bodyLarge.copy()
                            )
                        } else {
                            innerBox()
                        }
                    }
                },
            )

            Button(
                onClick = {
                    onActionTask(
                        ActionTask.SaveTask
                    )
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(46.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = typography.bodyLarge.copy(
                        color = colorScheme.onPrimary,
                    )
                )
            }

            Text(
                text = idTask,
                modifier = Modifier.padding(16.dp),
                color = colorScheme.surface

            )
        }
    }


}

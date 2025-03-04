@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todo_app_curso_platzi.presentation.detail


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.todo_app_curso_platzi.R
import com.example.todo_app_curso_platzi.domain.Category
import com.example.todo_app_curso_platzi.presentation.detail.provider.TaskScreenStatePreviewProvider
import com.example.todo_app_curso_platzi.ui.theme.TODO_APP_Curso_PlatziTheme
/*
@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    state: TaskScreenState,
) {

    var idExpanded by remember { mutableStateOf(false) }
    var isDescriptionFocused by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.task),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.done),
                    modifier = Modifier.padding(8.dp)
                )
                Checkbox(
                    checked = state.isTaskDone,
                    onCheckedChange = {

                    }
                )
                Spacer(
                    modifier = modifier.weight(1f)
                )
                Row {
                    Text(
                        text = state.category?.toString() ?: stringResource(R.string.category),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(
                                    8.dp
                                )
                            )
                            .padding(
                                8.dp
                            )
                    )
                    Box(
                        modifier = Modifier
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface,
                        )

                        DropdownMenu(
                            expanded = idExpanded,
                            onDismissRequest = { idExpanded = false },
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.surfaceContainerHighest
                            )
                        ) {
                            Column {
                                Category.entries.forEach { category ->
                                    Text(
                                        text = category.toString(),
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = MaterialTheme.colorScheme.onSurface
                                        ),
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .clickable {
                                                //TODO:
                                            }
                                    )
                                }
                            }
                        }


                    }
                }

            }
            BasicTextField(
                value = state.taskName,
                textStyle = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                maxLines = 1,
                onValueChange = {},
                decorationBox = { innerBox ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (state.taskName.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.task_name),
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                ),
                                style = MaterialTheme.typography.headlineLarge.copy(
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
                value = state.taskDescription ?: "",
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                maxLines = 15,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isDescriptionFocused = it.isFocused
                    },
                decorationBox = { innerBox ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        if (state.taskDescription.isNullOrEmpty() && !isDescriptionFocused) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.task_description),
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                ),
                                style = MaterialTheme.typography.bodyLarge.copy()
                            )
                        } else {
                            innerBox()
                        }
                    }
                },
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(46.dp)
            ) {
                Text(
                    text = stringResource(R.string.save),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun TaskScreenLightPreview(
    @PreviewParameter(TaskScreenStatePreviewProvider::class) state: TaskScreenState
){
    TODO_APP_Curso_PlatziTheme {
        TaskScreen(
            state = state,
        )
    }
}

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
fun TaskScreenDarkPreview(
    @PreviewParameter(TaskScreenStatePreviewProvider::class) state: TaskScreenState
){
    TODO_APP_Curso_PlatziTheme {
        TaskScreen(
            state = state
        )
    }
}
*/
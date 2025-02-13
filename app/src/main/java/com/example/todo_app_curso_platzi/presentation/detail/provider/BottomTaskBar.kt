@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todo_app_curso_platzi.presentation.detail.provider


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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.todo_app_curso_platzi.R
import com.example.todo_app_curso_platzi.domain.Category
import com.example.todo_app_curso_platzi.ui.theme.TODO_APP_Curso_PlatziTheme


@Composable
fun BottomTaskBar(
    modifier: Modifier = Modifier,
    state: TaskBottomSheetState,
) {

    var idExpanded by remember { mutableStateOf(false) }
    var isDescriptionFocused by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    Scaffold { paddingValues ->


        ModalBottomSheet(
            modifier = Modifier.padding(paddingValues),
            onDismissRequest = {},
            sheetState = sheetState
        ) {
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
                            modifier = Modifier
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = colorScheme.onSurface,
                            )

                            DropdownMenu(
                                expanded = idExpanded,
                                onDismissRequest = { idExpanded = false },
                                modifier = Modifier.background(
                                    color = colorScheme.surfaceContainerHighest
                                )
                            ) {
                                Column {
                                    Category.entries.forEach { category ->
                                        Text(
                                            text = category.toString(),
                                            style = typography.bodyMedium.copy(
                                                color = colorScheme.onSurface
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
                    textStyle = typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.onSurface,
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
                    value = state.taskDescription ?: "",
                    textStyle = typography.bodyLarge.copy(
                        color = colorScheme.onSurface,
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
                    onClick = {},
                    modifier = Modifier
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
            }
        }
    }

}

@Composable
@Preview
fun TaskScreenLightPreview(
    @PreviewParameter(TaskScreenStatePreviewProvider::class) state: TaskBottomSheetState
){
    TODO_APP_Curso_PlatziTheme {
        BottomTaskBar(
            state = state,
        )
    }
}

data class TaskBottomSheetState(
    val taskName: String = "",
    val taskDescription: String? = null,
    val category: Category? = null,
    val isTaskDone: Boolean = false,
)
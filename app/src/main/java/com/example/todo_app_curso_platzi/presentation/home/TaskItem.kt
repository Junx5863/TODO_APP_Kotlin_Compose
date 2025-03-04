package com.example.todo_app_curso_platzi.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.todo_app_curso_platzi.domain.Task
import com.example.todo_app_curso_platzi.presentation.home.provider.TaskItemPreviewProvider
import com.example.todo_app_curso_platzi.ui.theme.TODO_APP_Curso_PlatziTheme



@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    onClickItem: (String) -> Unit,
    onDeleteItem: (String) -> Unit,
    onToggleCompletion: (Task) -> Unit,
    task: Task,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onClickItem(
                    task.id
                )
            }
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer
            )
            .padding(
                horizontal = 8.dp
            ),
    ) {
        Checkbox(
            checked = task.isImportant,
            onCheckedChange = {
                onToggleCompletion(task)
            }
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(
                4.dp
            ),
            modifier = Modifier.padding(
                8.dp
            ).weight(
                1f
            )
        ) {
            Text(
                text = task.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall.copy(
                    textDecoration = if (task.isImportant) TextDecoration.LineThrough else TextDecoration.None
                ),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (!task.isImportant) {
                task.description?.let {
                    Text(
                        text = it,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                task.category?.let {
                    Text(
                        text = it.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

        }
        Box {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Task",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onDeleteItem(task.id)
                    }
            )
        }

    }

}

@Composable
@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO,
)
fun TaskItemPreviewLight(
    @PreviewParameter(TaskItemPreviewProvider::class) task: Task
) {
    TODO_APP_Curso_PlatziTheme {
        TaskItem(
            task = task,
            onClickItem = {},
            onDeleteItem = {},
            onToggleCompletion = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
fun TaskItemPreviewDark(
    @PreviewParameter(TaskItemPreviewProvider::class) task: Task
) {
    TODO_APP_Curso_PlatziTheme {
        TaskItem(
            task = task,
            onClickItem = {},
            onDeleteItem = {},
            onToggleCompletion = {}
        )
    }
}
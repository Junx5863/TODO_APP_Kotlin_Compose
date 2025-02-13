package com.example.todo_app_curso_platzi.presentation.detail.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.todo_app_curso_platzi.domain.Category


class TaskScreenStatePreviewProvider: PreviewParameterProvider<TaskBottomSheetState> {
    override val values: Sequence<TaskBottomSheetState>
        get() = sequenceOf(
            TaskBottomSheetState(
                taskName = "Task 1",
                taskDescription = "Description 1",
                isTaskDone = false,
                category = Category.WORK
            ),
            TaskBottomSheetState(
                taskName = "Task 2",
                taskDescription = "Description 2",
                isTaskDone = true,
                category = Category.WORK
            ),
            TaskBottomSheetState(
                taskName = "Task 3",
                taskDescription = "Description 3",
                isTaskDone = false,
                category = Category.OTHER
            ),
            TaskBottomSheetState(
                taskName = "Task 4",
                taskDescription = "Description 4",
                isTaskDone = true,
                category = null
            ),
            TaskBottomSheetState(
                taskName = "Task 5",
                taskDescription = null,
                isTaskDone = false,
                category = null
            )
        )
}
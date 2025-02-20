package com.example.todo_app_curso_platzi.presentation.home.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SummaryInfo(
    modifier: Modifier = Modifier,
    date: String = "March 9, 2024",
    taskSummary: String = "",
    completeTask: Int = 5,
    totalTask: Int = 10,
) {
    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(completeTask, totalTask) {
        if (totalTask == 0) {
            angleRatio.animateTo(
                targetValue = 0f
            )
            return@LaunchedEffect
        }
        angleRatio.animateTo(
            targetValue = (completeTask.toFloat() / totalTask.toFloat()),
            animationSpec = tween(
                durationMillis = 500
            )
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .weight(1.5f)) {
            Text(
                text = date,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = taskSummary,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )


        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .aspectRatio(1f)
                .weight(1f)
        ) {
            val colorBase = MaterialTheme.colorScheme.inversePrimary
            val progress = MaterialTheme.colorScheme.primary
            val strokeWidth = 16.dp
            Canvas(
                modifier = Modifier.aspectRatio(1f)
            ) {
                drawArc(
                    color = colorBase,
                    startAngle = 0f,
                    sweepAngle = 380f,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = 30f,
                        cap = StrokeCap.Round
                    )
                )

                if (completeTask <= totalTask) {
                    drawArc(
                        color = progress,
                        startAngle = 90f,
                        sweepAngle = (360f * angleRatio.value),
                        useCenter = false,
                        size = size,
                        style = Stroke(
                            width = 30f,
                            cap = StrokeCap.Round
                        )
                    )
                }
            }
            Text(
                text = "${(completeTask / totalTask.toFloat()).times(100).toInt()}%",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(
    showBackground = true,

    )
@Composable
fun PreviewSummaryInfo() {
    MaterialTheme {
        SummaryInfo(
            modifier = Modifier,
            completeTask = 5,
            totalTask = 10
        )
    }
}
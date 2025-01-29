package com.example.todo_app_curso_platzi.presentation.home

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


@Composable
fun SummaryInfo(
    modifier: Modifier = Modifier,
    date: String = "March 9, 2024",
    taskSummary: String = "You have 5 tasks",
    completeTask: Int,
    totalTask: Int,
) {
    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(completeTask, totalTask) {
        if( totalTask == 0 ){
            return@LaunchedEffect
        }
        angleRatio.animateTo(
            completeTask.toFloat()/totalTask.toFloat(),
            animationSpec = tween(
                durationMillis = 800
            )
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
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
        ) {
            val colorBase = MaterialTheme.colorScheme.inversePrimary
            val progress = MaterialTheme.colorScheme.primary
            val strokeWidth = 16.dp
            Canvas(
                modifier = Modifier.aspectRatio(1f)
            ){
                drawArc(
                    color = colorBase,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = size,
                    style = Stroke(
                        width = strokeWidth.toPx(),
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
                            width = strokeWidth.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                }
            }
            Text(
                text = "${(completeTask/totalTask.toFloat().times(100).toInt())}",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Preview(
    showBackground = true,

)
@Composable
fun  PreviewSumaryInfo(){
    MaterialTheme {
        SummaryInfo(
            modifier = Modifier,
            completeTask = 5,
            totalTask = 10
        )
    }
}
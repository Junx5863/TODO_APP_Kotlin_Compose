package com.example.todo_app_curso_platzi.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo_app_curso_platzi.domain.Category
import com.example.todo_app_curso_platzi.domain.Task
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId


@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val title: String,
    val description: String?,
    @ColumnInfo(name = "is_completed")
    val isImportant: Boolean,
    val date: Long
){

    fun toTask():Task{
        return Task(
            id = id,
            title = title,
            description = description,
            isImportant = isImportant,
            date = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(date),
                ZoneId.systemDefault()
            )
        )
    }
    companion object{
        fun fromTask(task: Task): TaskEntity{
            return TaskEntity(
                id = task.id,
                title = task.title,
                description = task.description,
                isImportant = task.isImportant,
                date = task.date!!.atZone(
                        ZoneId.systemDefault()
                    ).toInstant()
                    .toEpochMilli()
            )
        }
    }


}
package com.example.todo_app_curso_platzi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 1
)
abstract class TodoDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao


    companion object {
        @Volatile
        private var INSTANCE: TodoDataBase? = null

        fun getDatabase(context: Context): TodoDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDataBase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}
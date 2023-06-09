package com.example.sample_room_kotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDAO

    companion object {
        private var instance: TodoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TodoDatabase? {
            if (instance == null)
                synchronized(TodoDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "todo.db"
                    ).build()
                }
                return instance
            }
        }

        fun destroyInstance() {
            instance = null
    }
}
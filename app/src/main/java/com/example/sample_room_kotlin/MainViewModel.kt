package com.example.sample_room_kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel(application: Application): AndroidViewModel(application) {
    private var db: TodoDatabase? = TodoDatabase.getInstance(application)

    fun getAll(): LiveData<List<TodoEntity>> {
        return db!!.todoDao().getAll()
    }

    suspend fun insertTodo(todo: TodoEntity) {
        db!!.todoDao().insertTodo(todo)
    }
}
package com.example.sample_room_kotlin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM table_todo")
    fun getAll(): LiveData<List<TodoEntity>>

    @Insert
    fun insertTodo(todo: TodoEntity)

    @Update
    fun updateTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo : TodoEntity)
}
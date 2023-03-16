package com.example.sample_room_kotlin

import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT * FROM table_todo")
    fun getAll(): List<TodoEntity>

    @Insert
    fun insertTodo(todo: TodoEntity)

    @Update
    fun updateTodo(todo: TodoEntity)

    @Delete
    fun deleteTodo(todo : TodoEntity)
}
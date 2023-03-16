package com.example.sample_room_kotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_todo")
data class TodoEntity(var title: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}



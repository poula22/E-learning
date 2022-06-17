package com.example.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class StudentTodo(
    @PrimaryKey(autoGenerate = true)
    val studentId: Int? = null,
    @ColumnInfo
    val title: String? = null,
    @ColumnInfo
    val description: String? = null,
    @ColumnInfo
    val date: Date? = null,
    @ColumnInfo
    val isDone: Boolean? = false
)
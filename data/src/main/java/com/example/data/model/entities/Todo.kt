package com.example.data.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    @ColumnInfo
    val title:String?=null,
    @ColumnInfo
    val description:String?=null,
    @ColumnInfo
    val date: Date?=null,
    @ColumnInfo
    val isDone:Boolean?=false
)

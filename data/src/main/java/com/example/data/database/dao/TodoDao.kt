package com.example.data.database.dao

import androidx.room.*
import com.example.data.model.entities.Todo
import java.util.*

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)
    @Update
    fun updateTodo(todo: Todo)
    @Delete
    fun removeTodo(todo: Todo)
    @Query("DELETE FROM Todo")
    fun removeAll()
    @Query("select * from todo")
    fun getAllTodo():MutableList<Todo>
    @Query("select * from todo where date=:date")
    fun getTodoByDate(date: Date):MutableList<Todo>
}

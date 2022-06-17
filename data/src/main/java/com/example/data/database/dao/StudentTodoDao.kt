package com.example.data.database.dao

import androidx.room.*
import com.example.data.model.entities.StudentTodo
import com.example.data.model.entities.TeacherTodo
import java.util.*
@Dao
interface StudentTodoDao {
    @Insert
    fun addTodo(studentTodo: StudentTodo)
    @Update
    fun updateTodo(studentTodo: StudentTodo)
    @Delete
    fun removeTodo(studentTodo: StudentTodo)
    @Query("DELETE FROM StudentTodo")
    fun removeAll()
    @Query("select * from StudentTodo")
    fun getAllTodo():MutableList<TeacherTodo>
    @Query("select * from StudentTodo where date=:date")
    fun getTodoByDate(date: Date):MutableList<TeacherTodo>
}
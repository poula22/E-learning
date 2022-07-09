package com.example.data.database.dao

import androidx.room.*
import com.example.data.model.entities.TeacherTodo
import com.example.domain.model.TodoDTO
import java.util.*

@Dao
interface TeacherTodoDao {
    @Insert
    fun addTodo(teacherTodo: TeacherTodo)
    @Update
    fun updateTodo(teacherTodo: TeacherTodo)
    @Delete
    fun removeTodo(teacherTodo: TeacherTodo)
    @Query("DELETE FROM TeacherTodo")
    fun removeAll()
    @Query("select * from TeacherTodo")
    fun getAllTodo():MutableList<TeacherTodo>
    @Query("select * from TeacherTodo where date=:date")
    fun getTodoByDate(date: Date):MutableList<TeacherTodo>
}

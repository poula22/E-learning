package com.example.data.database.dao

import androidx.room.*
import com.example.data.model.entities.TeacherTodo
import java.util.*

@Dao
interface TeacherTodoDao {
    @Insert
    fun addTodo(teacherTodo: TeacherTodo)
    @Update
    fun updateTodo(teacherTodo: TeacherTodo)
    @Query("Delete from TeacherTodo where teacherId = :id")
    fun removeTodo(id: Int)
    @Query("DELETE FROM TeacherTodo")
    fun removeAll()
    @Query("select * from TeacherTodo")
    fun getAllTodo():MutableList<TeacherTodo>
    @Query("select * from TeacherTodo where date=:date")
    fun getTodoByDate(date: Date):MutableList<TeacherTodo>
}

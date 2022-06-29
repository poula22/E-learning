package com.example.domain.repos.data_sources

import com.example.domain.model.TodoDTO
import java.util.*

interface TodoOfflineDataSource{
    fun addTodo(todo: TodoDTO)
    fun updateTodo(todo: TodoDTO)
    fun removeTodo(todo: TodoDTO)
    fun removeAll()
    fun getAllTodo():MutableList<TodoDTO>
    fun getTodoByDate(date: Date):MutableList<TodoDTO>
}
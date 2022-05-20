package com.example.data.repos


import com.example.data.database.dao.TodoDao
import com.example.data.model.convertTo
import com.example.data.model.entities.Todo
import com.example.domain.model.TodoDTO
import com.example.domain.repos.TodoOfflineDataSource
import java.util.*

class TodoOfflineDataSourceImp(var todoDao: TodoDao):TodoOfflineDataSource {
    override fun addTodo(todo: TodoDTO) {
        todoDao.addTodo(todo.convertTo(Todo::class.java))
    }

    override fun updateTodo(todo: TodoDTO) {
        todoDao.updateTodo(todo.convertTo(Todo::class.java))
    }

    override fun removeTodo(todo: TodoDTO) {
        todoDao.removeTodo(todo.convertTo(Todo::class.java))
    }

    override fun getAllTodo(): MutableList<TodoDTO> {
        var list:MutableList<TodoDTO> = mutableListOf()
        todoDao.getAllTodo().forEach {
            var item=it.convertTo(TodoDTO::class.java)
            list.add(item)
        }
        return list
    }

    override fun getTodoByDate(date: Date): MutableList<TodoDTO> {
        var list:MutableList<TodoDTO> = mutableListOf()
        todoDao.getTodoByDate(date).forEach {
            var item=it.convertTo(TodoDTO::class.java)
            list.add(item)
        }
        return list
    }
}
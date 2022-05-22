package com.example.data.repos


import com.example.data.database.DataBase
import com.example.data.database.dao.TodoDao
import com.example.data.model.convertTo
import com.example.data.model.entities.Todo
import com.example.domain.model.TodoDTO
import com.example.domain.repos.TodoOfflineDataSource
import java.util.*

class TodoOfflineDataSourceImp(var dataBase: DataBase):TodoOfflineDataSource {
    override fun addTodo(todo: TodoDTO) {
        dataBase.todoDao().addTodo(todo.convertTo(Todo::class.java))
    }

    override fun updateTodo(todo: TodoDTO) {
        dataBase.todoDao().updateTodo(todo.convertTo(Todo::class.java))
    }

    override fun removeTodo(todo: TodoDTO) {
        dataBase.todoDao().removeTodo(todo.convertTo(Todo::class.java))
    }

    override fun removeAll() {
        dataBase.todoDao().removeAll()
    }

    override fun getAllTodo(): MutableList<TodoDTO> {
        var list:MutableList<TodoDTO> = mutableListOf()
        dataBase.todoDao().getAllTodo().forEach {
            var item=it.convertTo(TodoDTO::class.java)
            list.add(item)
        }
        return list
    }

    override fun getTodoByDate(date: Date): MutableList<TodoDTO> {
        var list:MutableList<TodoDTO> = mutableListOf()
        dataBase.todoDao().getTodoByDate(date).forEach {
            var item=it.convertTo(TodoDTO::class.java)
            list.add(item)
        }
        return list
    }
}
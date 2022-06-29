package com.example.data.repos

import com.example.domain.model.TodoDTO
import com.example.domain.repos.TodoRepository
import com.example.domain.repos.data_sources.TodoOfflineDataSource
import java.util.*

class TodoRepositoryImp(var offlineDataSource: TodoOfflineDataSource) : TodoRepository {
    override fun addTodo(todo: TodoDTO) {
        offlineDataSource.addTodo(todo)
    }

    override fun updateTodo(todo: TodoDTO) {
        offlineDataSource.updateTodo(todo)
    }

    override fun removeTodo(todo: TodoDTO) {
        offlineDataSource.removeTodo(todo)
    }

    override fun removeAll() {
        offlineDataSource.removeAll()
    }

    override fun getAllTodo(): MutableList<TodoDTO> {
        return offlineDataSource.getAllTodo()
    }

    override fun getTodoByDate(date: Date): MutableList<TodoDTO> {
        return offlineDataSource.getTodoByDate(date)
    }
}
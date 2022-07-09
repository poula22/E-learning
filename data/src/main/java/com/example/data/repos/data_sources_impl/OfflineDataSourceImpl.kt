package com.example.data.repos.data_sources_impl


import com.example.data.database.DataBase
import com.example.data.model.convertTo
import com.example.data.model.entities.StudentTodo
import com.example.data.model.entities.TeacherTodo
import com.example.domain.model.TodoDTO
import com.example.domain.repos.data_sources.TodoOfflineDataSource
import java.util.*

class TodoOfflineDataSourceImp(var dataBase: DataBase, var type: Int) :
    TodoOfflineDataSource {
    override fun addTodo(todo: TodoDTO) {
        if (type == 0) {
            dataBase.teacherTodoDao().addTodo(todo.convertTo(TeacherTodo::class.java))
        } else {
            dataBase.studentTodoDao().addTodo(todo.convertTo(StudentTodo::class.java))
        }

    }

    override fun updateTodo(todo: TodoDTO) {
        if (type == 0) {
            dataBase.teacherTodoDao().updateTodo(todo.convertTo(TeacherTodo::class.java))
        } else {
            dataBase.studentTodoDao().updateTodo(todo.convertTo(StudentTodo::class.java))

        }
    }

    override fun removeTodo(todo: TodoDTO):TodoDTO {
        if (type == 0) {
            dataBase.teacherTodoDao().removeTodo(todo.convertTo(TeacherTodo::class.java))
        } else {
            dataBase.studentTodoDao().removeTodo(todo.convertTo(StudentTodo::class.java))
        }
        return todo
    }

    override fun removeAll() {
        if (type == 0) {
            dataBase.teacherTodoDao().removeAll()
        } else {
            dataBase.studentTodoDao().removeAll()
        }
    }

    override fun getAllTodo(): MutableList<TodoDTO> {
        var list: MutableList<TodoDTO> = mutableListOf()
        if (type == 0) {
            dataBase.teacherTodoDao().getAllTodo().forEach {
                var item = it.convertTo(TodoDTO::class.java)
                list.add(item)
            }
        } else {
            dataBase.studentTodoDao().getAllTodo().forEach {
                var item = it.convertTo(TodoDTO::class.java)
                list.add(item)
            }
        }


        return list
    }

    override fun getTodoByDate(date: Date): MutableList<TodoDTO> {
        var list: MutableList<TodoDTO> = mutableListOf()
        if (type == 0) {
            dataBase.teacherTodoDao().getTodoByDate(date).forEach {
                var item = it.convertTo(TodoDTO::class.java)
                list.add(item)
            }
        } else {
            dataBase.studentTodoDao().getTodoByDate(date).forEach {
                var item = it.convertTo(TodoDTO::class.java)
                list.add(item)
            }
        }
        return list
    }
}


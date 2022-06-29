package com.example.domain.repos

import com.example.domain.model.OCRResponseDTO
import com.example.domain.model.TodoDTO
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import java.util.*


interface OCRRepository {
    suspend fun getTextFromImage(language: String, url: String): OCRResponseDTO
    suspend fun getTextFromImageReadApi(language: String? = null, url: String): ReadOperationResult
    suspend fun getTextFromImageReadApi(
        language: String? = null,
        image: ByteArray
    ): ReadOperationResult
}

interface TodoRepository {
    fun addTodo(todo: TodoDTO)
    fun updateTodo(todo: TodoDTO)
    fun removeTodo(todo: TodoDTO)
    fun removeAll()
    fun getAllTodo(): MutableList<TodoDTO>
    fun getTodoByDate(date: Date): MutableList<TodoDTO>
}



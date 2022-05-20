package com.example.domain.repos

import com.example.domain.model.*
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import kotlinx.coroutines.CoroutineScope
import java.util.*

interface CoursesRepository{
    suspend fun getCourses():List<CourseDTO>
}

interface CoursesOnlineDataSource{
    suspend fun getCoursesById():List<CourseDTO>
}

interface OCROnlineDataSource{
    suspend fun getTextFromImage(language:String,url:String): OCRResponseDTO
    suspend fun getTextFromImageReadApi(language:String?=null,url:String): ReadOperationResult
    suspend fun getTextFromImageReadApi(language: String?, image:ByteArray): ReadOperationResult
}

interface OCRRepository{
    suspend fun getTextFromImage(language:String,url:String):OCRResponseDTO
    suspend fun getTextFromImageReadApi(language: String?=null, url: String): ReadOperationResult
    suspend fun getTextFromImageReadApi(language: String?=null, image:ByteArray): ReadOperationResult
}
interface TodoOfflineDataSource{
    fun addTodo(todo: TodoDTO)
    fun updateTodo(todo: TodoDTO)
    fun removeTodo(todo: TodoDTO)
    fun getAllTodo():MutableList<TodoDTO>
    fun getTodoByDate(date: Date):MutableList<TodoDTO>
}
interface TodoRepository{
    fun addTodo(todo: TodoDTO)
    fun updateTodo(todo: TodoDTO)
    fun removeTodo(todo: TodoDTO)
    fun getAllTodo():MutableList<TodoDTO>
    fun getTodoByDate(date: Date):MutableList<TodoDTO>
}

interface FeaturesRepository{
    suspend fun getGrades():List<FeatureDTO>
}

interface FeaturesOnlineDataSource{
    suspend fun getCoursesById():List<FeatureDTO>
}

interface ChildrenRepository{
    suspend fun getGrades():List<ChildDTO>
}

interface ChildrenOnlineDataSource{
    suspend fun getChildren():List<ChildDTO>
}

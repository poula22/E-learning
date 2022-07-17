package com.example.domain.repos

import com.example.domain.model.*
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
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
    fun removeTodo(todo: TodoDTO):TodoDTO
    fun removeAll()
    fun getAllTodo(): MutableList<TodoDTO>
    fun getTodoByDate(date: Date): MutableList<TodoDTO>
}


interface MaterialRepository {

    // lesson functions
    suspend fun getAllLessons(): List<LessonResponseDTO>
    suspend fun addLesson(lesson: LessonResponseDTO): LessonResponseDTO
    suspend fun getLesson(id: Int): LessonResponseDTO
    suspend fun updateLesson(id: Int, lesson: LessonResponseDTO): LessonResponseDTO
    suspend fun deleteLesson(id: Int): Response<Void>
    suspend fun getLessonsByCourseId(courseId: Int): List<LessonResponseDTO>

    // content functions
    suspend fun addContent(body: RequestBody): Response<Void>
    suspend fun updateContent(id: Int, content: ContentResponseDTO): ContentResponseDTO
    suspend fun deleteContent(id: Int): ContentResponseDTO
    suspend fun getAllContents(): List<ContentResponseDTO>
    suspend fun getContentById(id: Int): ContentResponseDTO
    suspend fun getContentsByLessonId(lessonId: Int): List<ContentResponseDTO>
    suspend fun updateContentFileByContentId(
        contentId: Int,
        body: RequestBody
    ):ContentResponseDTO

}


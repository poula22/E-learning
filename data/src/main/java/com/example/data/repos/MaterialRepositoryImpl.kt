package com.example.data.repos

import com.example.domain.model.ContentResponseDTO
import com.example.domain.model.LessonResponseDTO
import com.example.domain.repos.MaterialRepository
import com.example.domain.repos.data_sources.ContentOnlineDataSource
import com.example.domain.repos.data_sources.LessonOnlineDataSource
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response

class MaterialRepositoryImpl(
    private val lessonOnlineDataSource: LessonOnlineDataSource,
    private val contentOnlineDataSource: ContentOnlineDataSource
) : MaterialRepository {
    override suspend fun getAllLessons(): List<LessonResponseDTO> {
        try {
            return lessonOnlineDataSource.getAllLessons()
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun addLesson(lesson: LessonResponseDTO): Response<Void> {
        try {
            return lessonOnlineDataSource.addLesson(lesson)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getLesson(id: Int): LessonResponseDTO {
        try {
            return lessonOnlineDataSource.getLesson(id)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateLesson(id: Int, lesson: LessonResponseDTO): LessonResponseDTO {
        try {
            return lessonOnlineDataSource.updateLesson(id, lesson)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteLesson(id: Int): LessonResponseDTO {
        try {
            return lessonOnlineDataSource.deleteLesson(id)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getLessonsByCourseId(courseId: Int): List<LessonResponseDTO> {
        try {
            return lessonOnlineDataSource.getLessonsByCourseId(courseId)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun addContent(body: RequestBody): Response<Void> {
        try {
            return contentOnlineDataSource.addContent(body)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateContent(id: Int, content: ContentResponseDTO): ContentResponseDTO {
        try {
            return contentOnlineDataSource.updateContent(id, content)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteContent(id: Int): ContentResponseDTO {
        try {
            return contentOnlineDataSource.deleteContent(id)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAllContents(): List<ContentResponseDTO> {
        try {
            return contentOnlineDataSource.getAllContents()
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getContentById(id: Int): ContentResponseDTO {
        try {
            return contentOnlineDataSource.getContentById(id)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getContentsByLessonId(lessonId: Int): List<ContentResponseDTO> {
        try {
            return contentOnlineDataSource.getContentsByLessonId(lessonId)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateContentFileByContentId(body: RequestBody) :ContentResponseDTO {
        try {
            return contentOnlineDataSource.updateContentFileByContentId(body)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}

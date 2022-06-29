package com.example.data.repos

import com.example.domain.model.ContentResponseDTO
import com.example.domain.model.LessonResponseDTO
import com.example.domain.repos.MaterialRepository
import com.example.domain.repos.data_sources.ContentOnlineDataSource
import com.example.domain.repos.data_sources.LessonOnlineDataSource

class MaterialRepositoryImpl(
    val lessonOnlineDataSource: LessonOnlineDataSource,
    val contentOnlineDataSource: ContentOnlineDataSource
) : MaterialRepository {
    override suspend fun getAllLessons(): List<LessonResponseDTO> {
        try {
            return lessonOnlineDataSource.getAllLessons()
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun addLesson(lesson: LessonResponseDTO): LessonResponseDTO {
        try {
            return lessonOnlineDataSource.addLesson(lesson)
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

    override suspend fun addContent(content: ContentResponseDTO): ContentResponseDTO {
        try {
            return contentOnlineDataSource.addContent(content)
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

}

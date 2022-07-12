package com.example.data.repos.data_sources_impl

import com.example.data.api.*
import com.example.data.api.microsoft_api.ocr.MicrosoftOCRApiManager
import com.example.data.api.microsoft_api.ocr.MicrosoftOCRWebService
import com.example.data.data_classes.URLOCR
import com.example.data.model.AssignmentResponse
import com.example.data.model.convertTo
import com.example.domain.model.*
import com.example.domain.repos.data_sources.*
import com.microsoft.azure.cognitiveservices.vision.computervision.implementation.ComputerVisionImpl
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadHeaders
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadInStreamHeaders
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// ML APIs
class OCROnlineDataSourceImp(val webService: MicrosoftOCRWebService) :
    OCROnlineDataSource {
    override suspend fun getTextFromImage(language: String, url: String): OCRResponseDTO {
        var u = URLOCR(url = url)
        var result = webService.getTextFromImage(language = language, url = u)
        return result.convertTo(OCRResponseDTO::class.java)
    }

    override suspend fun getTextFromImageReadApi(
        language: String?,
        url: String
    ): ReadOperationResult {
        println("-----------------------------------------------")
        println("Read with URL: $url")
        try {
            // Cast Computer Vision to its implementation to expose the required methods
            val vision: ComputerVisionImpl =
                MicrosoftOCRApiManager.client.computerVision() as ComputerVisionImpl

            // Read in remote image and response header
            val responseHeader: ReadHeaders =
                vision.readWithServiceResponseAsync(url, null)
                    .toBlocking()
                    .single()
                    .headers()

            // Extract the operation Id from the operationLocation header
            val operationLocation: String = responseHeader.operationLocation()
            println("Operation Location:$operationLocation")
            var result = MicrosoftOCRApiManager.getAndPrintReadResult(vision, operationLocation)
            return result
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            return ReadOperationResult()
        }
    }

    override suspend fun getTextFromImageReadApi(
        language: String?,
        image: ByteArray
    ): ReadOperationResult {

        try {
            // Cast Computer Vision to its implementation to expose the required methods
            val vision: ComputerVisionImpl =
                MicrosoftOCRApiManager.client.computerVision() as ComputerVisionImpl

            // Read in remote image and response header
            val responseHeader: ReadInStreamHeaders =
                vision.readInStreamWithServiceResponseAsync(image, null)
                    .toBlocking()
                    .single()
                    .headers()

            // Extract the operation Id from the operationLocation header
            val operationLocation: String = responseHeader.operationLocation()
            println("Operation Location:$operationLocation")
            var result = MicrosoftOCRApiManager.getAndPrintReadResult(vision, operationLocation)
            return result
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            return ReadOperationResult()
        }
    }

    private suspend fun getHeader(language: String? = null, url: String): String {
        var u = URLOCR(url = url)
        var result = webService.getTextFromImageReadAPI(url = u).headers().get("Operation-Location")
        var index = result?.indexOf("analyzeResults/")
        return result?.substring(index?.plus(15)!!)!!
    }

}


class AssignmentAnswerOnlineDataSourceImpl(
    val service: AssignmentAnswerWebService
) : AssignmentAnswerOnlineDataSource {
    override suspend fun addAssignmentAnswer(body: RequestBody): AssignmentAnswerResponseDTO {
        try {
            val response = service.addAssignmentAnswer(body)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateAssignmentAnswer(
        id: Int,
        assignmentAnswer: AssignmentAnswerResponseDTO
    ): AssignmentAnswerResponseDTO {
        try {
            val response = service.updateAssignmentAnswer(id, assignmentAnswer)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteAssignmentAnswer(id: Int): AssignmentAnswerResponseDTO {
        try {
            val response = service.deleteAssignmentAnswer(id)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAllAssignmentAnswer(): List<AssignmentAnswerResponseDTO> {
        try {
            val response = service.getAllAssignmentAnswer()
            return response.map { it.convertTo(AssignmentAnswerResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAssignmentAnswerById(id: Int): AssignmentAnswerResponseDTO {
        try {
            val response = service.getAssignmentAnswerById(id)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAssignmentAnswersByAssignmentId(assignmentId: Int): List<AssignmentAnswerDetailsResponseDTO> {
        try {
            val response = service.getAssignmentAnswersByAssignmentId(assignmentId)
            return response.map { it.convertTo(AssignmentAnswerDetailsResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAssignmentAnswerByStudentIdByAssignmentId(
        studentID: Int,
        assignmentId: Int
    ): AssignmentAnswerResponseDTO {
        try {
            val response =
                service.getAssignmentAnswerByStudentIdByAssignmentId(studentID, assignmentId)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateAssignmentAnswerFileByAssignmentAnswerId(
        id: Int,
        body: RequestBody
    ): AssignmentAnswerResponseDTO {
        //callback
        try {
            val response = service.updateAssignmentAnswerFileByAssignmentAnswerId(id, body)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


    override suspend fun updateMultipleAssignedGrades(grades: List<AssignmentAnswerResponseDTO>): AssignmentAnswerResponseDTO {
        try {
            val response = service.updateMultipleAssignedGrades(grades)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateAssignmentGrade(grade: AssignmentAnswerResponseDTO): AssignmentAnswerResponseDTO {
        try {
            val response = service.updateAssignmentGrade(grade)
            return response.convertTo(AssignmentAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class AssignmentOnlineDataSourceImpl(
    val service: AssignmentWebService,
    override var callResult: AssignmentOnlineDataSource.CallResult? = null
) :
    AssignmentOnlineDataSource {
    override suspend fun addAssignment(assignment: AssignmentResponseDTO): AssignmentResponseDTO {
        try {
            val response = service.addAssignment(assignment)
            return response.convertTo(AssignmentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateAssignment(
        id: Int,
        assignment: AssignmentResponseDTO
    ): AssignmentResponseDTO {
        try {
            val response = service.updateAssignment(id, assignment)
            return response.convertTo(AssignmentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


    override suspend fun deleteAssignment(id: Int): AssignmentResponseDTO {
        try {
            val response = service.deleteAssignment(id)
            return response.convertTo(AssignmentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAllAssignment(): List<AssignmentResponseDTO> {
        try {
            val response = service.getAllAssignment()
            return response.map { it.convertTo(AssignmentResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAssignmentById(id: Int): AssignmentResponseDTO {
        try {
            val response = service.getAssignmentById(id)
            return response.convertTo(AssignmentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAssignmentsByCourseId(courseId: Int): List<AssignmentResponseDTO> {
        try {
            val response = service.getAssignmentsByCourseId(courseId)
            return response.map { it.convertTo(AssignmentResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAssignmentsByCourseIdForStudent(
        courseId: Int,
        studentId: Int
    ): List<AssignmentDetailsResponseDTO> {
        try {
            val response = service.getAssignmentsByCourseIdForStudent(courseId, studentId)
            return response.map { it.convertTo(AssignmentDetailsResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override fun updateAssignmentFileByAssignmentId(
        assignmentId: Int,
        file: MultipartBody.Part
    ) {
        //callback
        try {
            val response = service.updateAssignmentFileByAssignmentId(assignmentId, file)
            response.enqueue(
                object : Callback<AssignmentResponse> {
                    override fun onResponse(
                        call: Call<AssignmentResponse>,
                        response: Response<AssignmentResponse>
                    ) {
                        response.body()?.convertTo(AssignmentResponseDTO::class.java)
                            ?.let { callResult?.getDTOData(it) }
                    }

                    override fun onFailure(call: Call<AssignmentResponse>, t: Throwable) {

                    }

                }
            )
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAssignmentGradesByCourseIdByStudentIdForTeacher(
        courseId: Int,
        studentId: Int
    ): List<AssignmentResponseDTO> {
        try {
            val response =
                service.getAssignmentGradesByCourseIdByStudentIdForTeacher(courseId, studentId)
            return response.map { it.convertTo(AssignmentResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}

class ContentOnlineDataSourceImpl(val service: ContentWebService) : ContentOnlineDataSource {
    override suspend fun addContent(body: RequestBody): Response<Void> {
        try {
            val response = service.addContent(body)
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateContent(id: Int, content: ContentResponseDTO): ContentResponseDTO {
        try {
            val response = service.updateContent(id, content)
            return response.convertTo(ContentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteContent(id: Int): ContentResponseDTO {
        try {
            val response = service.deleteContent(id)
            return response.convertTo(ContentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAllContents(): List<ContentResponseDTO> {
        try {
            val response = service.getAllContents()
            return response.map { it.convertTo(ContentResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getContentById(id: Int): ContentResponseDTO {
        try {
            val response = service.getContentById(id)
            return response.convertTo(ContentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getContentsByLessonId(lessonId: Int): List<ContentResponseDTO> {
        try {
            val response = service.getContentsByLessonId(lessonId)
            return response.map { it.convertTo(ContentResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateContentFileByContentId(body: RequestBody): ContentResponseDTO {
        //callback
        try {
            val response = service.updateContentFileByContentId(body)
            return response.convertTo(ContentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class CourseOnlineDataSourceImpl(
    val service: CourseWebService
) : CourseOnlineDataSource {
    override suspend fun addCourse(course: CourseResponseDTO): Response<Void> {
        try {

            val response = service.addCourse(course)
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateCourse(id: Int, course: CourseResponseDTO): CourseResponseDTO {
        try {
            val response = service.updateCourse(id, course)
            return response.convertTo(CourseResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteCourse(id: Int): CourseResponseDTO {
        try {
            val response = service.deleteCourse(id)
            return response.convertTo(CourseResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun joinCourse(courseId: Int, studentId: Int): Response<Void> {
        try {
            val response = service.joinCourse(courseId, studentId)
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun dropCourse(courseId: Int, studentId: Int): Response<Void> {
        try {
            val response = service.dropCourse(courseId, studentId)
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getCoursesByTeacherId(teacherId: Int): List<CourseResponseDTO> {
        try {
            val response = service.getCoursesByTeacherId(teacherId)
            return response.map { it.convertTo(CourseResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getCoursesByStudentId(studentId: Int): List<CourseResponseDTO> {
        try {
            val response = service.getCoursesByStudentId(studentId)
            return response.map { it.convertTo(CourseResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getCourse(courseId: Int): CourseResponseDTO {
        try {
            val response = service.getCourse(courseId)
            return response.convertTo(CourseResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateCourseImageByCourseId(
        courseId: Int,
        body: RequestBody
    ): CourseResponseDTO {
        //callback
        try {
            val response = service.updateCourseImageByCourseId(courseId, body)
            return response.convertTo(CourseResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


    override suspend fun getAllCourses(): List<CourseResponseDTO> {
        try {
            val response = service.getAllCourses()
            return response.map { it.convertTo(CourseResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class FeatureOnlineDataSourceImpl(val service: FeatureWebService) :
    FeatureOnlineDataSource {
    override suspend fun addFeature(feature: FeatureResponseDTO): FeatureResponseDTO {
        try {
            val response = service.addFeature(feature)
            return response.convertTo(FeatureResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateFeature(id: Int, feature: FeatureResponseDTO): FeatureResponseDTO {
        try {
            val response = service.updateFeature(id, feature)
            return response.convertTo(FeatureResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteFeature(id: Int): FeatureResponseDTO {
        try {
            val response = service.deleteFeature(id)
            return response.convertTo(FeatureResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getFeature(id: Int): FeatureResponseDTO {
        try {
            val response = service.getFeature(id)
            return response.convertTo(FeatureResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAllFeatures(): List<FeatureResponseDTO> {
        try {
            val response = service.getAllFeatures()
            return response.map { it.convertTo(FeatureResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}


class LessonOnlineDataSourceImpl(val service: LessonWebService) :
    LessonOnlineDataSource {
    override suspend fun addLesson(lesson: LessonResponseDTO): Response<Void> {
        try {
            val response = service.addLesson(lesson)
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getLesson(id: Int): LessonResponseDTO {
        try {
            val response = service.getLesson(id)
            return response.convertTo(LessonResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateLesson(id: Int, lesson: LessonResponseDTO): LessonResponseDTO {
        try {
            val response = service.updateLesson(id, lesson)
            return response.convertTo(LessonResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteLesson(id: Int): LessonResponseDTO {
        try {
            val response = service.deleteLesson(id)
            return response.convertTo(LessonResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getLessonsByCourseId(courseId: Int): List<LessonResponseDTO> {
        try {
            val response = service.getLessonsByCourseId(courseId)
            return response.map { it.convertTo(LessonResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getAllLessons(): List<LessonResponseDTO> {
        try {
            val response = service.getAllLessons()
            return response.map { it.convertTo(LessonResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}


class ParentOnlineDataSourceImpl(val service: ParentWebService) :
    ParentOnlineDataSource {
    override suspend fun addParent(parent: ParentResponseDTO): UserResponseDTO {
        try {
            val response = service.addParent(parent)
            return response.convertTo(UserResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun addStudentsByEmailToParent(
        parentId: Int,
        studentEmail: String
    ): List<ParentResponseDTO> {
        try {
            val response = service.addStudentsByEmailToParent(parentId, studentEmail)
            return response.map { it.convertTo(ParentResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class QuestionAnswerOnlineDataSourceImpl(val service: QuestionAnswerWebService) :
    QuestionAnswerOnlineDataSource {
    override suspend fun getAllQuestionAnswers(): List<QuestionAnswerResponseDTO> {
        try {
            val response = service.getAllQuestionAnswers()
            return response.map { it.convertTo(QuestionAnswerResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun addQuestionAnswer(questionAnswer: QuestionAnswerResponseDTO): QuestionAnswerResponseDTO {
        try {
            val response = service.addQuestionAnswer(questionAnswer)
            return response.convertTo(QuestionAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateQuestionAnswer(
        id: Int,
        questionAnswer: QuestionAnswerResponseDTO
    ): QuestionAnswerResponseDTO {
        try {
            val response = service.updateQuestionAnswer(id, questionAnswer)
            return response.convertTo(QuestionAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteQuestionAnswer(id: Int): QuestionAnswerResponseDTO {
        try {
            val response = service.deleteQuestionAnswer(id)
            return response.convertTo(QuestionAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuestionAnswer(id: Int): QuestionAnswerResponseDTO {
        try {
            val response = service.getQuestionAnswer(id)
            return response.convertTo(QuestionAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getCorrectQuestionAnswerOrNot(
        questionId: Int,
        questionAnswerId: Int
    ): QuestionAnswerResponseDTO {
        try {
            val response = service.getCorrectQuestionAnswerOrNot(questionId, questionAnswerId)
            return response.convertTo(QuestionAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuestionAnswersByQuestionId(questionId: Int): List<QuestionAnswerResponseDTO> {
        try {
            val response = service.getQuestionAnswersByQuestionId(questionId)
            return response.map { it.convertTo(QuestionAnswerResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuestionAnswerByQuestionIdByStudentId(
        studentId: Int,
        questionId: Int
    ): QuestionAnswerResponseDTO {
        try {
            val response = service.getQuestionAnswerByQuestionIdByStudentId(studentId, questionId)
            return response.convertTo(QuestionAnswerResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun postMultipleQuestionAnswers(questionAnswers: List<QuestionAnswerResponseDTO>): List<QuestionAnswerResponseDTO> {
        try {
            val response = service.postMultipleQuestionAnswers(questionAnswers)
            return response.map { it.convertTo(QuestionAnswerResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}


class QuestionChoiceOnlineDataSourceImpl(val service: QuestionChoiceWebService) :
    QuestionChoiceOnlineDataSource {
    override suspend fun getAllQuestionChoices(): List<QuestionChoiceResponseDTO> {
        try {
            val response = service.getAllQuestionChoices()
            return response.map { it.convertTo(QuestionChoiceResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun addQuestionChoices(questionChoice: QuestionChoiceResponseDTO): Response<Void> {
        try {
            val response = service.addQuestionChoices(questionChoice)
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateQuestionChoices(
        id: Int,
        questionChoice: QuestionChoiceResponseDTO
    ): QuestionChoiceResponseDTO {
        try {
            val response = service.updateQuestionChoices(id, questionChoice)
            return response.convertTo(QuestionChoiceResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteQuestionChoices(id: Int): QuestionChoiceResponseDTO {
        try {
            val response = service.deleteQuestionChoices(id)
            return response.convertTo(QuestionChoiceResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuestionChoices(id: Int): QuestionChoiceResponseDTO {
        try {
            val response = service.getQuestionChoices(id)
            return response.convertTo(QuestionChoiceResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuestionChoicesByQuestionId(questionId: Int): List<QuestionChoiceResponseDTO> {
        try {
            val response = service.getQuestionChoicesByQuestionId(questionId)
            return response.map { it.convertTo(QuestionChoiceResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun postMultipleQuestionChoices(questionChoices: List<QuestionChoiceResponseDTO>): Response<Void> {
        try {
            val response = service.postMultipleQuestionChoices(questionChoices)
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class QuestionOnlineDataSourceImpl(val service: QuestionWebService) :
    QuestionOnlineDataSource {
    override suspend fun getQuestions(): List<QuestionResponseDTO> {
        try {
            val response = service.getQuestions()
            return response.map { it.convertTo(QuestionResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


    override suspend fun addQuestion(question: QuestionResponseDTO): QuestionResponseDTO {
        try {
            val response = service.addQuestion(question)
            return response.convertTo(QuestionResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateQuestion(
        id: Int,
        question: QuestionResponseDTO
    ): QuestionResponseDTO {
        try {
            val response = service.updateQuestion(id, question)
            return response.convertTo(QuestionResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteQuestion(id: Int): QuestionResponseDTO {
        try {
            val response = service.deleteQuestion(id)
            return response.convertTo(QuestionResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuestion(id: Int): QuestionResponseDTO {
        try {
            val response = service.getQuestion(id)
            return response.convertTo(QuestionResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuestionsByQuizId(id: Int): List<QuizDetailsResponseDTO> {
        try {
            val response = service.getQuestionsByQuizId(id)
            return response.map { it.convertTo(QuizDetailsResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}


class QuizGradeOnlineDataSourceImpl(val service: QuizGradeWebService) :
    QuizGradeOnlineDataSource {
    override suspend fun getAllQuizGrades(): List<QuestionResponseDTO> {
        try {
            val response = service.getAllQuizGrades()
            return response.map { it.convertTo(QuestionResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun quizGrades(quizGrades: QuizGradeResponseDTO): QuizGradeResponseDTO {
        try {
            val response = service.quizGrades(quizGrades)
            return response.convertTo(QuizGradeResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteQuizGrades(id: Int): QuizGradeResponseDTO {
        try {
            val response = service.deleteQuizGrades(id)
            return response.convertTo(QuizGradeResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateQuizGrades(
        id: Int,
        QuizGrades: QuizGradeResponseDTO
    ): QuizGradeResponseDTO {
        try {
            val response = service.updateQuizGrades(id, QuizGrades)
            return response.convertTo(QuizGradeResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuizGrades(id: Int): QuizGradeResponseDTO {
        try {
            val response = service.getQuizGrades(id)
            return response.convertTo(QuizGradeResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuizGradesByQuizId(id: Int): List<QuizGradeResponseDTO> {
        try {
            val response = service.getQuizGradesByQuizId(id)
            return response.map { it.convertTo(QuizGradeResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuizGradeByQuizIdByStudentId(
        studentId: Int,
        quizId: Int
    ): QuizGradeResponseDTO {
        try {
            val response = service.getQuizGradeByQuizIdByStudentId(studentId, quizId)
            return response.convertTo(QuizGradeResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun quizGradeAdder(studentId: Int, quizId: Int): QuizGradeResponseDTO {
        try {
            val response = service.quizGradeAdder(studentId, quizId)
            return response.convertTo(QuizGradeResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class QuizOnlineDataSourceImpl(val service: QuizWebService) :
    QuizOnlineDataSource {
    override suspend fun getAllQuizzes(): List<QuizResponseDTO> {
        try {
            val response = service.getAllQuizzes()
            return response.map { it.convertTo(QuizResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun createQuiz(quiz: QuizResponseDTO): QuizResponseDTO {
        try {
            val response = service.createQuiz(quiz)
            return response.convertTo(QuizResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuizById(id: Int): QuizResponseDTO {
        try {
            val response = service.getQuizById(id)
            return response.convertTo(QuizResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


    override suspend fun updateQuiz(
        id: Int,
        quiz: QuizResponseDTO
    ): QuizResponseDTO {
        try {
            val response = service.updateQuiz(id, quiz)
            return response.convertTo(QuizResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteQuiz(id: Int) {
        try {
            service.deleteQuiz(id)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuizzesByCourseId(courseId: Int): List<QuizResponseDTO> {
        try {
            val response = service.getQuizzesByCourseId(courseId)
            return response.map { it.convertTo(QuizResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getQuizGradesByCourseIdAndStudentIdForTeacher(
        courseId: Int,
        studentId: Int
    ): List<QuizResponseDTO> {
        try {
            val response =
                service.getQuizGradesByCourseIdAndStudentIdForTeacher(courseId, studentId)
            return response.map { it.convertTo(QuizResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class StudentOnlineDataSourceImpl(val service: StudentWebService) :
    StudentOnlineDataSource {
    override suspend fun addStudent(student: StudentResponseDTO): UserResponseDTO {
        try {
            val response = service.addStudent(student)
            return response.convertTo(UserResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getStudentsByParentId(parentId: Int): StudentResponseDTO {
        try {
            val response = service.getStudentsByParentId(parentId)
            return response.convertTo(StudentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getStudentsByCourseId(courseId: Int): List<StudentResponseDTO> {
        try {
            val response = service.getStudentByCourseId(courseId)
            return response.map { it.convertTo(StudentResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getStudentByEmail(email: String): StudentResponseDTO {
        try {
            val response = service.getStudentByEmail(email)
            return response.convertTo(StudentResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class TeacherOnlineDataSourceImpl(val service: TeacherWebService) :
    TeacherOnlineDataSource {
    override suspend fun addTeacher(teacher: TeacherResponseDTO): Response<Void> {
        try {

            if (teacher.profilePic == null) {
                teacher.profilePic = ""
            }

            val response = service.addTeacher(teacher.firstName?.let {
                MultipartBody.Part.createFormData(
                    "firstName",
                    it
                )
            }, teacher.lastName?.let {
                MultipartBody.Part.createFormData(
                    "lastName",
                    it
                )
            }, teacher.phone?.let {
                MultipartBody.Part.createFormData(
                    "phone",
                    it
                )
            }, MultipartBody.Part.createFormData(
                "profilePic",
                teacher.profilePic!!
            ), teacher.role?.let {
                MultipartBody.Part.createFormData(
                    "role",
                    it
                )
            }, teacher.emailAddress?.let {
                MultipartBody.Part.createFormData(
                    "emailAddress",
                    it
                )
            }, teacher.password?.let {
                MultipartBody.Part.createFormData(
                    "password",
                    it
                )
            })
            return response
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}


class UserOnlineDataSourceImpl(val service: UserWebService) :
    UserOnlineDataSource {
    override suspend fun getAllUsers(): List<UserResponseDTO> {
        try {
            val response = service.getAllUsers()
            return response.map { it.convertTo(UserResponseDTO::class.java) }
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getUserById(id: Int): UserResponseDTO {
        try {
            val response = service.getUserById(id)
            return response.convertTo(UserResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun updateUserById(id: Int, user: UserResponseDTO): UserResponseDTO {
        try {
            val response = service.updateUserById(id, user)
            return response.convertTo(UserResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun deleteUserById(id: Int): UserResponseDTO {
        try {
            val response = service.deleteUserById(id)
            return response.convertTo(UserResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun logIn(email: String, password: String): UserResponseDTO {
        try {
            val response = service.logIn(email, password)
            return response.convertTo(UserResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun logInTest(user: UserResponseDTO): UserResponseDTO {
        try {
            val response = service.logInTest(user)
            return response.convertTo(UserResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class SummarizationOnlineDataSourceImpl(val service: SummarizationWebService) :
    SummarizationOnlineDataSource {
    override suspend fun getSummarizationForText(
        summarizationResponse: SummarizationTextRequestDTO
    ): SummarizationResponseDTO {
        try {
            val response = service.getSummarizationForText(summarizationResponse)
            return response.convertTo(SummarizationResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

    override suspend fun getSummarizationForUrl(
        summarizationResponse: SummarizationUrlRequestDTO
    ): SummarizationResponseDTO {
        try {
            val response = service.getSummarizationForUrl(summarizationResponse)
            return response.convertTo(SummarizationResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }


}


class RecitationParagraphOnlineDataSourceImpl(val service: RecitationWebService) :
    RecitationParagraphOnlineDataSource {
    override suspend fun getSimilarity(request: RecitationParagraphRequestDTO)
            : RecitationParagraphResponseDTO {
        try {
            val response = service.getSimilarity(request)
            return response.convertTo(RecitationParagraphResponseDTO::class.java)
        } catch (throwable: Throwable) {
            throw throwable
        }
    }

}





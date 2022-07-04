package com.example.domain.repos.data_sources

import com.example.domain.model.*
import com.microsoft.azure.cognitiveservices.vision.computervision.models.ReadOperationResult
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response

// ALL ONLINE DATA SOURCES SHOULD BE HERE

interface OCROnlineDataSource {
    suspend fun getTextFromImage(language: String, url: String): OCRResponseDTO
    suspend fun getTextFromImageReadApi(language: String? = null, url: String): ReadOperationResult
    suspend fun getTextFromImageReadApi(language: String?, image: ByteArray): ReadOperationResult
}


// All online data source interfaces for all json items


interface AssignmentAnswerOnlineDataSource {
    suspend fun addAssignmentAnswer(assignmentAnswer: AssignmentAnswerResponseDTO): AssignmentAnswerResponseDTO
    suspend fun updateAssignmentAnswer(
        id: Int, assignmentAnswer: AssignmentAnswerResponseDTO
    ): AssignmentAnswerResponseDTO

    suspend fun deleteAssignmentAnswer(id: Int): AssignmentAnswerResponseDTO
    suspend fun getAllAssignmentAnswer(): List<AssignmentAnswerResponseDTO>
    suspend fun getAssignmentAnswerById(id: Int): AssignmentAnswerResponseDTO
    suspend fun getAssignmentAnswersByAssignmentId(assignmentId: Int): List<AssignmentAnswerDetailsResponseDTO>
    suspend fun getAssignmentAnswerByStudentIdByAssignmentId(
        studentID: Int,
        assignmentId: Int
    ): AssignmentAnswerResponseDTO

    suspend fun updateAssignmentAnswerFileByAssignmentAnswerId(
        id: Int,
        file: MultipartBody.Part
    ): AssignmentAnswerResponseDTO

    suspend fun updateMultipleAssignedGrades(grades: List<AssignmentAnswerResponseDTO>): AssignmentAnswerResponseDTO

    suspend fun updateAssignmentGrade(grade: AssignmentAnswerResponseDTO): AssignmentAnswerResponseDTO


}

//interface AssignmentGradeOnlineDataSource {
//    suspend fun addAssignmentGrade(assignmentGrade: AssignmentGradeResponseDTO): AssignmentGradeResponseDTO
//    suspend fun updateAssignmentGrade(
//        id: Int, assignmentGrade: AssignmentGradeResponseDTO
//    ): AssignmentGradeResponseDTO
//
//    suspend fun deleteAssignmentGrade(id: Int): AssignmentGradeResponseDTO
//    suspend fun getAllAssignmentGrade(): List<AssignmentGradeResponseDTO>
//    suspend fun getAssignmentGradeByAnswerId(assignmentAnswerId: Int): AssignmentGradeResponseDTO
//
//}

interface AssignmentOnlineDataSource {
    suspend fun addAssignment(assignment: AssignmentResponseDTO): AssignmentResponseDTO
    suspend fun updateAssignment(
        id: Int,
        assignment: AssignmentResponseDTO
    ): AssignmentResponseDTO

    suspend fun deleteAssignment(id: Int): AssignmentResponseDTO
    suspend fun getAllAssignment(): List<AssignmentResponseDTO>
    suspend fun getAssignmentById(id: Int): AssignmentResponseDTO
    suspend fun getAssignmentsByCourseId(courseId: Int): List<AssignmentResponseDTO>
    suspend fun getAssignmentsByCourseIdForStudent(
        courseId: Int,
        studentId: Int
    ): List<AssignmentDetailsResponseDTO>

    suspend fun updateAssignmentFileByAssignmentId(
        assignmentId: Int,
        file: MultipartBody.Part
    ): AssignmentResponseDTO

}


interface ContentOnlineDataSource {
    suspend fun addContent(content: ContentResponseDTO): ContentResponseDTO
    suspend fun updateContent(id: Int, content: ContentResponseDTO): ContentResponseDTO
    suspend fun deleteContent(id: Int): ContentResponseDTO
    suspend fun getAllContents(): List<ContentResponseDTO>
    suspend fun getContentById(id: Int): ContentResponseDTO
    suspend fun getContentsByLessonId(lessonId: Int): List<ContentResponseDTO>
    suspend fun updateContentFileByContentId(
        contentId: Int,
        file: MultipartBody.Part
    ): ContentResponseDTO
}

interface CourseOnlineDataSource {
    suspend fun getAllCourses(): List<CourseResponseDTO>
    suspend fun addCourse(course: CourseResponseDTO): Response<Void>
    suspend fun updateCourse(id: Int, course: CourseResponseDTO): CourseResponseDTO
    suspend fun deleteCourse(id: Int): CourseResponseDTO
    suspend fun joinCourse(courseId: Int, studentId: Int): Response<Void>
    suspend fun dropCourse(courseId: Int, studentId: Int): Response<Void>
    suspend fun getCoursesByTeacherId(teacherId: Int): List<CourseResponseDTO>
    suspend fun getCoursesByStudentId(studentId: Int): List<CourseResponseDTO>
    suspend fun getCourse(courseId: Int): CourseResponseDTO
    fun updateCourseImageByCourseId(
        courseId: Int,
        image: MultipartBody.Part
    )
    var callResult:CallResult?
    interface CallResult{
        fun getDTOData(data: CourseResponseDTO)
    }
}

interface FeatureOnlineDataSource {
    suspend fun getAllFeatures(): List<FeatureResponseDTO>
    suspend fun addFeature(feature: FeatureResponseDTO): FeatureResponseDTO
    suspend fun deleteFeature(id: Int): FeatureResponseDTO
    suspend fun getFeature(id: Int): FeatureResponseDTO
    suspend fun updateFeature(id: Int, feature: FeatureResponseDTO): FeatureResponseDTO
}

interface LessonOnlineDataSource {
    suspend fun getAllLessons(): List<LessonResponseDTO>
    suspend fun addLesson(lesson: LessonResponseDTO): LessonResponseDTO
    suspend fun getLesson(id: Int): LessonResponseDTO
    suspend fun updateLesson(id: Int, lesson: LessonResponseDTO): LessonResponseDTO
    suspend fun deleteLesson(id: Int): LessonResponseDTO
    suspend fun getLessonsByCourseId(courseId: Int): List<LessonResponseDTO>
}

interface ParentOnlineDataSource {
    suspend fun addParent(parent: ParentResponseDTO): UserResponseDTO
    suspend fun addStudentsByEmailToParent(
        parentId: Int,
        studentEmail: String
    ): List<ParentResponseDTO>
}

interface QuestionAnswerOnlineDataSource {
    suspend fun getAllQuestionAnswers(): List<QuestionAnswerResponseDTO>
    suspend fun addQuestionAnswer(questionAnswer: QuestionAnswerResponseDTO): QuestionAnswerResponseDTO
    suspend fun updateQuestionAnswer(
        id: Int,
        questionAnswer: QuestionAnswerResponseDTO
    ): QuestionAnswerResponseDTO

    suspend fun deleteQuestionAnswer(id: Int): QuestionAnswerResponseDTO
    suspend fun getQuestionAnswer(id: Int): QuestionAnswerResponseDTO
    suspend fun getCorrectQuestionAnswerOrNot(
        questionId: Int, questionAnswerId: Int
    ): QuestionAnswerResponseDTO

    suspend fun getQuestionAnswersByQuestionId(questionId: Int): List<QuestionAnswerResponseDTO>
    suspend fun getQuestionAnswerByQuestionIdByStudentId(
        studentId: Int,
        questionId: Int
    ): QuestionAnswerResponseDTO

    suspend fun postMultipleQuestionAnswers(questionAnswers: List<QuestionAnswerResponseDTO>): List<QuestionAnswerResponseDTO>

}

interface QuestionChoiceOnlineDataSource {
    suspend fun getAllQuestionChoices(): List<QuestionChoiceResponseDTO>
    suspend fun addQuestionChoices(questionChoice: QuestionChoiceResponseDTO): QuestionChoiceResponseDTO
    suspend fun updateQuestionChoices(
        id: Int,
        questionChoice: QuestionChoiceResponseDTO
    ): QuestionChoiceResponseDTO

    suspend fun deleteQuestionChoices(id: Int): QuestionChoiceResponseDTO
    suspend fun getQuestionChoices(id: Int): QuestionChoiceResponseDTO
    suspend fun getQuestionChoicesByQuestionId(questionId: Int): List<QuestionChoiceResponseDTO>
    suspend fun postMultipleQuestionChoices(questionChoice: QuestionChoiceResponseDTO): QuestionChoiceResponseDTO
}

interface QuestionOnlineDataSource {
    suspend fun getQuestions(): List<QuestionResponseDTO>
    suspend fun addQuestion(question: QuestionResponseDTO): QuestionResponseDTO
    suspend fun deleteQuestion(id: Int): QuestionResponseDTO
    suspend fun updateQuestion(id: Int, question: QuestionResponseDTO): QuestionResponseDTO
    suspend fun getQuestion(id: Int): QuestionResponseDTO
    suspend fun getQuestionsByQuizId(id: Int): List<QuizDetailsResponseDTO>

}

interface QuizGradeOnlineDataSource {
    suspend fun getAllQuizGrades(): List<QuestionResponseDTO>
    suspend fun quizGrades(quizGrades: QuizGradeResponseDTO): QuizGradeResponseDTO
    suspend fun deleteQuizGrades(id: Int): QuizGradeResponseDTO
    suspend fun updateQuizGrades(id: Int, QuizGrades: QuizGradeResponseDTO): QuizGradeResponseDTO
    suspend fun getQuizGrades(id: Int): QuizGradeResponseDTO
    suspend fun getQuizGradesByQuizId(id: Int): List<QuizGradeResponseDTO>
    suspend fun getQuizGradeByQuizIdByStudentId(studentId: Int, quizId: Int): QuizGradeResponseDTO
    suspend fun quizGradeAdder(studentId: Int, quizId: Int): QuizGradeResponseDTO
}

interface QuizOnlineDataSource {
    suspend fun getAllQuizzes(): List<QuizResponseDTO>
    suspend fun createQuiz(quiz: QuizResponseDTO): QuizResponseDTO
    suspend fun getQuizById(id: Int): QuizResponseDTO
    suspend fun updateQuiz(id: Int, quiz: QuizResponseDTO): QuizResponseDTO
    suspend fun deleteQuiz(id: Int)
    suspend fun getQuizzesByCourseId(courseId: Int): List<QuizResponseDTO>

}


interface StudentOnlineDataSource {
    suspend fun addStudent(student: StudentResponseDTO): UserResponseDTO
    suspend fun getStudentsByParentId(parentId: Int): StudentResponseDTO
    suspend fun getStudentByCourseId(courseId: Int): List<StudentResponseDTO>
    suspend fun getStudentByEmail(email: String): StudentResponseDTO
}

interface TeacherOnlineDataSource {
    suspend fun addTeacher(teacher: TeacherResponseDTO): Response<Void>
}

interface TodoOnlineDataSource {
    suspend fun getToDoLists(): ToDoListResponse
    suspend fun addToDoList(toDoList: TodoDTO): ToDoListResponse
    suspend fun updateToDoList(id: Int, toDoList: TodoDTO): ToDoListResponse
    suspend fun deleteToDoList(id: Int): ToDoListResponse
    suspend fun getToDoList(id: Int): ToDoListResponse
    suspend fun getToDoListsByUserId(userId: Int): ToDoListResponse

}

interface UserOnlineDataSource {
    suspend fun getAllUsers(): List<UserResponseDTO>
    suspend fun getUserById(id: Int): UserResponseDTO
    suspend fun updateUserById(id: Int, user: UserResponseDTO): UserResponseDTO
    suspend fun deleteUserById(id: Int): UserResponseDTO
    suspend fun logIn(email: String, password: String): UserResponseDTO
    suspend fun logInTest(user: UserResponseDTO): UserResponseDTO

}


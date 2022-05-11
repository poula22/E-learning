package com.example.data.model

import com.google.gson.annotations.SerializedName

data class StudentResponse(

	@field:SerializedName("StudentResponse")
	val studentResponse: List<StudentResponseItem?>? = null
)

data class StudentResponseItem(

	@field:SerializedName("studentLastName")
	val studentLastName: String? = null,

	@field:SerializedName("parent")
	val parent: Parent? = null,

	@field:SerializedName("courses")
	val courses: List<Any?>? = null,

	@field:SerializedName("notes")
	val notes: List<Any?>? = null,

	@field:SerializedName("profilePic")
	val profilePic: String? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("latestPassedLessons")
	val latestPassedLessons: List<Any?>? = null,

	@field:SerializedName("assignmentAnswers")
	val assignmentAnswers: List<Any?>? = null,

	@field:SerializedName("features")
	val features: List<Any?>? = null,

	@field:SerializedName("emailAddress")
	val emailAddress: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("questionAnswers")
	val questionAnswers: List<Any?>? = null,

	@field:SerializedName("toDoLists")
	val toDoLists: List<Any?>? = null,

	@field:SerializedName("quizAnswers")
	val quizAnswers: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("studentPhone")
	val studentPhone: String? = null,

	@field:SerializedName("studentFirstName")
	val studentFirstName: String? = null
)

data class Parent(
	val any: Any? = null
)

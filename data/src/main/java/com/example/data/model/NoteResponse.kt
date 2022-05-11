package com.example.data.model

import com.google.gson.annotations.SerializedName

data class NoteResponse(

	@field:SerializedName("NoteResponse")
	val noteResponse: List<NoteResponseItem?>? = null
)


data class NoteResponseItem(

	@field:SerializedName("studentID")
	val studentID: Int? = null,

	@field:SerializedName("noteText")
	val noteText: String? = null,

	@field:SerializedName("student")
	val student: Student? = null,

	@field:SerializedName("lesson")
	val lesson: Lesson? = null,

	@field:SerializedName("lessonID")
	val lessonID: Int? = null,

	@field:SerializedName("noteID")
	val noteID: Int? = null
)



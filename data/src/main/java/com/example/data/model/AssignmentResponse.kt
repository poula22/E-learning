package com.example.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AssignmentResponse(

	@field:SerializedName("filePath")
	val filePath: String? = null,

	@field:SerializedName("grade")
	val grade: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("endTime")
	val endTime: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("courseId")
	val courseId: Int? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null
)

package com.example.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CourseResponse(

	@field:SerializedName("courseName")
	val courseName: String? = null,

	@field:SerializedName("courseImage")
	val courseImage: String? = null,

	@field:SerializedName("teacherId")
	val teacherId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("courseDescription")
	val courseDescription: String? = null
) : Serializable

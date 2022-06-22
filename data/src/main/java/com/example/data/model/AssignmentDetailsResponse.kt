package com.example.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AssignmentDetailsResponse(

	@field:SerializedName("submitted")
	val submitted: Boolean? = null,

	@field:SerializedName("assignedGrade")
	val assignedGrade: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("endTime")
	val endTime: String? = null,

	@field:SerializedName("title")
	val title: String? = null
): Serializable

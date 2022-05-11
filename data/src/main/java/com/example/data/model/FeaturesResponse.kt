package com.example.data.model

import com.google.gson.annotations.SerializedName

data class FeaturesResponse(

	@field:SerializedName("FeaturesResponse")
	val featuresResponse: List<FeaturesResponseItem?>? = null
)

data class FeaturesResponseItem(

	@field:SerializedName("studentID")
	val studentID: Int? = null,

	@field:SerializedName("newName")
	val newName: String? = null,

	@field:SerializedName("student")
	val student: Student? = null,

	@field:SerializedName("oldName")
	val oldName: String? = null,

	@field:SerializedName("oldPath")
	val oldPath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("newPath")
	val newPath: String? = null
)

data class Student(
	val any: Any? = null
)

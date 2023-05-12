package com.example.data.model

import com.google.gson.annotations.SerializedName

data class FeatureResponse(

	@field:SerializedName("studentId")
	val studentId: Int? = null,

	@field:SerializedName("newName")
	val newName: String? = null,

	@field:SerializedName("oldName")
	val oldName: String? = null,

	@field:SerializedName("oldPath")
	val oldPath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("newPath")
	val newPath: String? = null
)

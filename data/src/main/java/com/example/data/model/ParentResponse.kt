package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ParentResponse(

	@field:SerializedName("ParentResponse")
	val parentResponse: List<ParentResponseItem?>? = null
)

data class ParentResponseItem(

	@field:SerializedName("studentID")
	val studentID: Int? = null,

	@field:SerializedName("parentFirstName")
	val parentFirstName: String? = null,

	@field:SerializedName("emailAddress")
	val emailAddress: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("parentLastName")
	val parentLastName: String? = null,

	@field:SerializedName("student")
	val student: Student? = null,

	@field:SerializedName("profilePic")
	val profilePic: String? = null,

	@field:SerializedName("toDoLists")
	val toDoLists: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("parentPhone")
	val parentPhone: String? = null
)



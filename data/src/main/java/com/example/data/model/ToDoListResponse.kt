package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ToDoListResponse(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("important")
	val important: Boolean? = null,

	@field:SerializedName("notes")
	val notes: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("urgent")
	val urgent: Boolean? = null,

	@field:SerializedName("done")
	val done: Boolean? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)

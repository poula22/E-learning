package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ToDoListResponse(

	@field:SerializedName("ToDoListResponse")
	val toDoListResponse: List<ToDoListResponseItem?>? = null
)

data class ToDoListResponseItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("important")
	val important: Boolean? = null,

	@field:SerializedName("notes")
	val notes: String? = null,

	@field:SerializedName("loginInfoID")
	val loginInfoID: Int? = null,

	@field:SerializedName("loginInfo")
	val loginInfo: LoginInfo? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("time")
	val time: String? = null,

	@field:SerializedName("urgent")
	val urgent: Boolean? = null,

	@field:SerializedName("done")
	val done: Boolean? = null
)

data class LoginInfo(
	val any: Any? = null
)

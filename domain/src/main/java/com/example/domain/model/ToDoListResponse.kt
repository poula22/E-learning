package com.example.domain.model

data class ToDoListResponse(
	val date: String? = null,
	val important: Boolean? = null,
	val notes: String? = null,
	val description: String? = null,
	val id: Int? = null,
	val title: String? = null,
	val urgent: Boolean? = null,
	val done: Boolean? = null,
	val userId: Int? = null
)

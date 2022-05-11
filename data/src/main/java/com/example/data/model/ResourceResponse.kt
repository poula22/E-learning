package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ResourceResponse(

	@field:SerializedName("ResourceResponse")
	val resourceResponse: List<ResourceResponseItem?>? = null
)

data class ResourceResponseItem(

	@field:SerializedName("path")
	val path: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null
)

package com.example.data.model

import com.google.gson.annotations.SerializedName

data class AnnouncementResponse(

	@field:SerializedName("AnnouncementResponse")
	val announcementResponse: List<AnnouncementResponseItem?>? = null
)

data class AnnouncementResponseItem(

	@field:SerializedName("courses")
	val courses: List<Any?>? = null,

	@field:SerializedName("announcementId")
	val announcementId: Int? = null,

	@field:SerializedName("announcementContent")
	val announcementContent: String? = null,

	@field:SerializedName("postDate")
	val postDate: String? = null
)

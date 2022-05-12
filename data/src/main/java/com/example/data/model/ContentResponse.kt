package com.example.data.model

import com.google.gson.annotations.SerializedName


data class ContentResponse(

    @field:SerializedName("path")
    val path: String? = null,

    @field:SerializedName("fileName")
    val fileName: String? = null,

    @field:SerializedName("lesson")
    val lesson: Lesson? = null,

    @field:SerializedName("lessonID")
    val lessonID: Int? = null,

    @field:SerializedName("showDate")
    val showDate: String? = null,

    @field:SerializedName("fileID")
    val fileID: Int? = null
)

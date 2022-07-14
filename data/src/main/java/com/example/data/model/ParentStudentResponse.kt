package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ParentStudentResponse(

    @field:SerializedName("parentFirstName")
    val parentFirstName: String? = null,

    @field:SerializedName("parentProfilePic")
    val parentProfilePic: String? = null,

    @field:SerializedName("parentLastName")
    val parentLastName: String? = null,

    @field:SerializedName("parentId")
    val parentId: Int? = null
)

package com.example.data.model

import com.google.gson.annotations.SerializedName

data class ParentStudentRequest(

    @field:SerializedName("studentEmail")
    val studentEmail: String? = null,

    @field:SerializedName("parentId")
    val parentId: Int? = null
)

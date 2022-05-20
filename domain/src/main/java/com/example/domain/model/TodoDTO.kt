package com.example.domain.model


import java.util.*

class TodoDTO (
    val id:Int?=null,
    val title:String?=null,
    val description:String?=null,
    val date: Date?=null,
    val isDone:Boolean?=false
)
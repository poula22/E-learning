package com.example.extentions

import java.util.*

fun Calendar.clearTime():Calendar{
    this.set(Calendar.HOUR_OF_DAY,0)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.SECOND)
    this.clear(Calendar.MILLISECOND)
    return this
}
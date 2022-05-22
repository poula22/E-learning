package com.example.data.model.entities.converters

import android.util.Log
import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromDate(date: Date):Long{
        Log.v("day::",date.toString())
        return date.time
    }
    @TypeConverter
    fun toDate(time:Long):Date{
        return Date(time)
    }
}
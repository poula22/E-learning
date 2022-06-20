package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.dao.StudentTodoDao
import com.example.data.database.dao.TeacherTodoDao
import com.example.data.model.entities.StudentTodo
import com.example.data.model.entities.TeacherTodo
import com.example.data.model.entities.converters.DateConverter

@Database(entities = [TeacherTodo::class,StudentTodo::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun teacherTodoDao(): TeacherTodoDao
    abstract fun studentTodoDao(): StudentTodoDao
    companion object{
        private var myDataBase:DataBase?=null
        fun init(context:Context){
            if (myDataBase==null)
                myDataBase=Room.databaseBuilder(context,DataBase::class.java,"Todo_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

        }
        fun getInstance():DataBase{
            return myDataBase!!
        }
    }
}
package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.database.dao.TodoDao
import com.example.data.model.entities.Todo
import com.example.data.model.entities.converters.DateConverter

@Database(entities = [Todo::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
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
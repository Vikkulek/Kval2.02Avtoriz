package com.example.kval202

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Avia::class], version = 1)
abstract class TableDB:RoomDatabase(){
    abstract fun getDao():DAO

    companion object{
        fun getDB(context: Context):TableDB{
            return Room.databaseBuilder(
                context.applicationContext,TableDB::class.java,"avia.db"
            ).build()
        }
    }
}
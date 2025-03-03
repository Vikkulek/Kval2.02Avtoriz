package com.example.kval202

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Avia::class, FavoriteAvia::class], version = 2)
abstract class TableDB : RoomDatabase() {
    abstract fun getDao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: TableDB? = null

        fun getDB(context: Context): TableDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TableDB::class.java,
                    "avia.db"
                ).fallbackToDestructiveMigration() // Удаляет старую БД при изменении структуры (если миграции нет)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

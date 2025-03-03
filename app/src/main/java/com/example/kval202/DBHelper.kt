package com.example.kval202

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "kval2", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY, login TEXT, email TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        writableDatabase.insert("users", null, ContentValues().apply {
            put("login", user.login)
            put("email", user.email)
            put("password", user.password)
        })
    }

    fun getUser(login: String, password: String): Boolean {
        return readableDatabase.rawQuery("SELECT * FROM users WHERE login = ? AND password = ?", arrayOf(login, password)).use {
            it.moveToFirst()
        }
    }
    fun updateLoginByCurrentLogin(currentLogin: String, newLogin: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("login", newLogin)

        // Обновляем запись в таблице по текущему логину
        val rowsUpdated = db.update("users", values, "login = ?", arrayOf(currentLogin))

        db.close()

        // Если строк обновлено больше 0, то обновление прошло успешно
        return rowsUpdated > 0
    }
}

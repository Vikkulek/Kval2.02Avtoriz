package com.example.kval202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val login = intent.getStringExtra("login") ?: return
        findViewById<EditText>(R.id.editTextNewLogin).setText(login)

        findViewById<Button>(R.id.buttonExit).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.buttonTable).setOnClickListener {
            startActivity(Intent(this, Table::class.java))
            finish()
        }

        findViewById<Button>(R.id.buttonSaveLogin).setOnClickListener {
            val newLogin = findViewById<EditText>(R.id.editTextNewLogin).text.toString().trim()
            if (newLogin.isNotEmpty()) {
                DBHelper(this).updateLoginByCurrentLogin(login, newLogin)
                Toast.makeText(this, "Логин обновлен", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

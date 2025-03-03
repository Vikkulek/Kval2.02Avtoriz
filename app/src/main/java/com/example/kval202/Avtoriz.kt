package com.example.kval202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Avtoriz : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avtoriz)

        val loginField: EditText = findViewById(R.id.editTextLogin)
        val passwordField: EditText = findViewById(R.id.editTextPassword)
        findViewById<Button>(R.id.buttonEnter).setOnClickListener {
            val login = loginField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            if (login.isNotEmpty() && password.isNotEmpty()) {
                if (DBHelper(this).getUser(login, password)) {
                    startActivity(Intent(this, Profile::class.java).apply {
                        putExtra("login", login)
                    })
                    finish()
                } else {
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<TextView>(R.id.TextViewReg).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

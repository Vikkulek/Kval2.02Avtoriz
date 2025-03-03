package com.example.kval202

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginField: EditText = findViewById(R.id.editTextLogin)
        val emailField: EditText = findViewById(R.id.editTextEmail)
        val passwordField: EditText = findViewById(R.id.editTextPassword)

        findViewById<Button>(R.id.buttonReg).setOnClickListener {
            val login = loginField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            if (login.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                DBHelper(this).addUser(User(login, email, password))
                Toast.makeText(this, "Регистрация успешна", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Profile::class.java).apply {
                    putExtra("login", login)
                })
                finish()
            } else {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<TextView>(R.id.TextViewAvto).setOnClickListener {
            startActivity(Intent(this, Avtoriz::class.java))
        }
    }
}

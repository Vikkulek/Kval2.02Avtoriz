package com.example.kval202

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Avtoriz : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_avtoriz)

        val userLogin: EditText = findViewById(R.id.editTextLogin)
        val userPassword: EditText = findViewById(R.id.editTextPassword)
        val button: Button = findViewById(R.id.buttonEnter)

        val goToReg: TextView = findViewById(R.id.TextViewReg)
        goToReg.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == ""  || password == "" )
                Toast.makeText(this,"Заполните все поля", Toast.LENGTH_LONG).show()
            else {
                val db = DBHelper(this,null)

                val isHere = db.getUser(login,password)
                if (isHere){
                    Toast.makeText(this,"Пользователь $login Авторизован",Toast.LENGTH_SHORT).show()

                    userLogin.text.clear()
                    userPassword.text.clear()

//                    val intent = Intent(this, TableActivity::class.java)
//                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"Пользователь $login ненайден",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
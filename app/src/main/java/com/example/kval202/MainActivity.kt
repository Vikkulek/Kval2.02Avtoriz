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
import com.example.kval202.R.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_main)

        val userLogin:EditText = findViewById(R.id.editTextLogin)
        val userEmail:EditText = findViewById(R.id.editTextEmail)
        val userPassword:EditText = findViewById(R.id.editTextPassword)
        val button:Button = findViewById(R.id.buttonReg)
        val goToAvtoriz:TextView = findViewById(R.id.TextViewAvto)

        goToAvtoriz.setOnClickListener{
            val intent = Intent(this, Avtoriz::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == "" || email == "" || password == "" )
                Toast.makeText(this,"Заполните все поля",Toast.LENGTH_LONG).show()
            else {
                val user = User(login,email,password)
                val db = DBHelper(this,null)
                db.addUser(user)
                Toast.makeText(this,"Регистрация $login выполнена",Toast.LENGTH_SHORT).show()

//                val intent = Intent(this, TableActivity::class.java)
//                startActivity(intent)

                userLogin.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()
            }


        }

    }
}
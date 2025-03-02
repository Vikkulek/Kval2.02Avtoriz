package com.example.kval202

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import java.util.Calendar

class Profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Login:EditText= findViewById(R.id.editTextNewLogin)
        val Email:TextView = findViewById(R.id.textViewEmail)
        val Exit:Button = findViewById(R.id.buttonExit)
        val tableLook: Button = findViewById(R.id.buttonTable)
        val buttonSaveLogin: Button = findViewById(R.id.buttonSaveLogin)
//        val nameEditText: EditText = findViewById(R.id.nameEditText)
//        val resourceTypeEditText: EditText = findViewById(R.id.resourceTypeEditText)
//        val dateButton: Button = findViewById(R.id.dateButton)
//        val timeButton: Button = findViewById(R.id.timeButton)
//        val additionalInfoEditText: EditText = findViewById(R.id.additionalInfoEditText)
//        val submitButton: Button = findViewById(R.id.submitButton)
//        val selectedDateTextView: TextView = findViewById(R.id.selectedDateTextView)
//        val selectedTimeTextView: TextView = findViewById(R.id.selectedTimeTextView)



        val login = intent.getStringExtra("login")
        val email = intent.getStringExtra("email")

        Login.setText(login)
        Email.text = email

        val dbHelper = DBHelper(this, null)

//        val calendar = Calendar.getInstance()
//
//        dateButton.setOnClickListener {
//            val datePicker = DatePickerDialog(
//                this,
//                { _, year, month, dayOfMonth ->
//                    selectedDateTextView.text = "$dayOfMonth/${month + 1}/$year"
//                },
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH)
//            )
//            datePicker.show()
//        }
//
//        timeButton.setOnClickListener {
//            val timePicker = TimePickerDialog(
//                this,
//                { _, hour, minute ->
//                    selectedTimeTextView.text = "$hour:$minute"
//                },
//                calendar.get(Calendar.HOUR_OF_DAY),
//                calendar.get(Calendar.MINUTE),
//                true
//            )
//            timePicker.show()
//        }
//
//        submitButton.setOnClickListener {
//            val name = nameEditText.text.toString()
//            val resourceType = resourceTypeEditText.text.toString()
//            val date = selectedDateTextView.text.toString()
//            val time = selectedTimeTextView.text.toString()
//           // val additionalInfo = additionalInfoEditText.text.toString()
//
//            if (name.isNotEmpty() && resourceType.isNotEmpty() && date.isNotEmpty() && time.isNotEmpty()) {
//                Toast.makeText(this, "Заявка отправлена", Toast.LENGTH_SHORT).show()
//                // Здесь можно добавить сохранение данных в базу SQLite
//            } else {
//                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
//            }
//        }


        Exit.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        tableLook.setOnClickListener{
            val intent = Intent(this, Table::class.java)
            startActivity(intent)
            finish()
        }

        buttonSaveLogin.setOnClickListener {
            val newLogin = Login.text.toString() // Получаем новый логин из поля EditText

            if (newLogin.isNotEmpty()) {
                // Получаем текущий логин из Intent
                val currentLogin = intent.getStringExtra("login")

                if (currentLogin != null) {
                    // Обновляем логин в базе данных
                    val updated = dbHelper.updateLoginByCurrentLogin(currentLogin, newLogin)

                    if (updated) {
                        // Обновляем логин на экране
                        Login.setText(newLogin)

                        // Показываем сообщение об успешном изменении логина
                        Toast.makeText(this, "Логин обновлен!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Ошибка: не удалось найти пользователя", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

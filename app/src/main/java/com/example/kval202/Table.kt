package com.example.kval202

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.kval202.databinding.ActivityTableBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStreamReader

fun loadJsonData(context: Context, filename: String): List<Avia> {
    val json = context.assets.open(filename).use { inputStream ->
        InputStreamReader(inputStream).use { reader ->
            reader.readText()
        }
    }

    val type = object : TypeToken<List<Avia>>() {}.type
    return Gson().fromJson(json, type)
}

class Table : AppCompatActivity() {
    lateinit var binding: ActivityTableBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val db = TableDB.getDB(this)
        db.getDao().getAllAvia().asLiveData().observe(this){itList ->
            binding.textViewList.text = ""
            itList.forEach{
                val text = "Id:${it.searchToken} ," +
                        "From:${it.startCity} ," +
                        "FromCode:${it.startCityCode}," +
                        "To:${it.endCity}," +
                        "ToCode:${it.endCityCode}," +
                        "1Date:${it.startDate}," +
                        "1Date:${it.endDate}," +
                        "price:${it.price}.\n\n"
                binding.textViewList.append(text)
            }
        }
        binding.buttonSave.setOnClickListener{
            val avia = Avia(
                binding.editTextTextsearchToken.text.toString(),
                binding.editTextTextendCity.text.toString(),
                binding.editTextTextstartCity.text.toString(),
                binding.editTextTextstartCityCode.text.toString(),
                binding.editTextTextprice.text.toString(),
                binding.editTextTextendCityCode.text.toString(),
                binding.editTextTextstartDate.text.toString(),
                binding.editTextTextendDate.text.toString()
            )
            Thread{
                db.getDao().insertAvia(avia)
            }.start()

        }

        binding.buttonDelete.setOnClickListener {
            val tokenToDelete = binding.editTextDeleteToken.text.toString()

            if (tokenToDelete.isNotEmpty()) {
                // Запускаем удаление записи в фоновом потоке
                GlobalScope.launch(Dispatchers.IO) {
                    val aviaToDelete = db.getDao().getAviaByToken(tokenToDelete)
                    if (aviaToDelete != null) {
                        // Удаляем запись из базы данных
                        db.getDao().deleteAvia(aviaToDelete)

                        // После удаления обновляем список в UI
                        launch(Dispatchers.Main) {
                            // Обновляем список в TextView
                            Toast.makeText(applicationContext, "Запись удалена!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Если записи с таким токеном нет
                        launch(Dispatchers.Main) {
                            Toast.makeText(applicationContext, "Запись с таким токеном не найдена!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Введите токен для удаления", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
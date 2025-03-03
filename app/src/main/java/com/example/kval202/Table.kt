package com.example.kval202

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.kval202.databinding.ActivityTableBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Table : AppCompatActivity() {
    private lateinit var binding: ActivityTableBinding
    private val db by lazy { TableDB.getDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.getDao().getAllAvia().asLiveData().observe(this) { list ->
            binding.textViewList.text = list.joinToString("\n\n") {
                "Id:${it.searchToken}, From:${it.startCity}, To:${it.endCity}," +
                        " Price:${it.price},ToCode:${it.endCityCode}, 1Date:${it.startDate}," +
                        "EndDate:${it.endDate}, Price:${it.price}\""
            }
        }

        binding.buttonSave.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                db.getDao().insertAvia(Avia(
                    binding.editTextTextsearchToken.text.toString(),
                    binding.editTextTextstartCity.text.toString(),
                    binding.editTextTextendCity.text.toString(),
                    binding.editTextTextstartCityCode.text.toString(),
                    binding.editTextTextprice.text.toString(),
                    binding.editTextTextendCityCode.text.toString(),
                    binding.editTextTextstartDate.text.toString(),
                    binding.editTextTextendDate.text.toString()
                ))
            }
        }

        binding.buttonFavorite.setOnClickListener {
            val token = binding.editTextFavoriteToken.text.toString()
            if (token.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val avia = db.getDao().getAviaByToken(token)
                    if (avia != null) {
                        db.getDao().insertFavorite(
                            FavoriteAvia(
                                avia.searchToken,
                                avia.startCity,
                                avia.endCity,
                                avia.price,
                                avia.endCityCode,
                                avia.startDate,
                                avia.endDate
                            )
                        )
                        launch(Dispatchers.Main) {
                            Toast.makeText(this@Table, "Добавлено в избранное", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@Table, Favorite::class.java)
                            startActivity(intent)
                        }
                    } else {
                        launch(Dispatchers.Main) {
                            Toast.makeText(this@Table, "Запись не найдена", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Введите токен", Toast.LENGTH_SHORT).show()
            }
        }


    }
}

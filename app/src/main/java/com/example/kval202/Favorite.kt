package com.example.kval202

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.kval202.databinding.ActivityFavoriteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Favorite : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val db by lazy { TableDB.getDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.getDao().getAllFavorites().asLiveData().observe(this) { list ->
            binding.textViewList.text = list.joinToString("\n\n") {
                "Token: ${it.searchToken}, From: ${it.startCity}, To: ${it.endCity}, Price: ${it.price}, " +
                        "ToCode: ${it.endCityCode}, StartDate: ${it.startDate}, EndDate: ${it.endDate}"
            }
        }
        binding.buttonDeleteFavorite.setOnClickListener {
            val token = binding.editTextFavoriteToken.text.toString()
            if (token.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    db.getDao().deleteFavoriteByToken(token)
                    launch(Dispatchers.Main) {
                        Toast.makeText(this@Favorite, "Удалено из избранного", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Введите токен", Toast.LENGTH_SHORT).show()
            }
        }

    }
}

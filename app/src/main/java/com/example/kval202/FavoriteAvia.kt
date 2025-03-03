package com.example.kval202

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteAvia(
    @PrimaryKey val searchToken: String,
    val startCity: String,
    val endCity: String,
    val price: String,
    val endCityCode: String,
    val startDate: String,
    val endDate: String
)

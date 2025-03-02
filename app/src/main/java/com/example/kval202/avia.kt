package com.example.kval202

import android.content.Context
import android.media.RouteListingPreference
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.io.InputStreamReader
import java.io.Serializable

@Entity(tableName = "aviaData")
data class Avia(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "searchToken")
    val searchToken: String,
    @ColumnInfo(name = "startCity")
    val startCity: String,
    @ColumnInfo(name = "startCityCode")
    val startCityCode: String,
    @ColumnInfo(name = "endCity")
    val endCity: String,
    @ColumnInfo(name = "endCityCode")
    val endCityCode: String,
    @ColumnInfo(name = "startDate")
    val startDate: String,
    @ColumnInfo(name = "endDate")
    val endDate: String,
    @ColumnInfo(name = "price")
    val price: String,
) : Serializable


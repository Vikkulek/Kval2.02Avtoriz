//package com.example.kval202
//
//import android.content.Context
//import android.media.RouteListingPreference
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import com.google.gson.Gson
//import java.io.InputStreamReader
//import java.io.Serializable
//
//data class Avia(
//    val startCity: String,
//    val startCityCode: String,
//    val endCity: String,
//    val endCityCode: String,
//    val startDate: String,
//    val endDate: String,
//    val price: Int,
//    val searchToken: String
//) : Serializable
//
//fun parseJson(context: Context, resourceId: Int): MutableList<Avia> {
//    val inputStream = context.resources.openRawResource(resourceId)
//    val reader = InputStreamReader(inputStream)
//    val gson = Gson()
//    val listType = object : com.google.gson.reflect.TypeToken<List<Avia>>() {}.type
//    return gson.fromJson(reader, listType) as MutableList<Avia>
//}

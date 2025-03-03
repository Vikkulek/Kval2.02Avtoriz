package com.example.kval202

import android.content.ContentValues
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert
    fun insertAvia(avia:Avia)
    @Query("SELECT * FROM aviaData")
    fun getAllAvia(): Flow<List<Avia>>
    @Update
    suspend fun updateAvia(avia: Avia)
    @Delete
    suspend fun deleteAvia(avia: Avia)

    @Query("DELETE FROM aviaData WHERE searchToken = :token")
    suspend fun deleteAviaByToken(token: String)
    @Query("SELECT * FROM aviaData WHERE searchToken = :token LIMIT 1")
    suspend fun getAviaByToken(token: String): Avia?
    @Query("SELECT * FROM favorite_table")
    fun getAllFavorites(): Flow<List<FavoriteAvia>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteAvia)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteAvia)

    @Query("DELETE FROM favorite_table WHERE searchToken = :token")
    suspend fun deleteFavoriteByToken(token: String)

}
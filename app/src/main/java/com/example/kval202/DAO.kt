package com.example.kval202

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
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

}
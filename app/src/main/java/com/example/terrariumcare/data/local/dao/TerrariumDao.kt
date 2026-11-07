package com.example.terrariumcare.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.terrariumcare.data.local.entities.TerrariumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TerrariumDao {
    @Query("SELECT * FROM terrariums")
    fun getAllTerrariums(): Flow<List<TerrariumEntity>>

    @Query("SELECT * FROM terrariums WHERE id = :terrariumId")
    suspend fun getTerrariumById(terrariumId: Long): TerrariumEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerrarium(terrarium: TerrariumEntity): Long

    @Update
    suspend fun updateTerrarium(terrarium: TerrariumEntity)

    @Delete
    suspend fun deleteTerrarium(terrarium: TerrariumEntity)

    @Query("SELECT * FROM terrariums WHERE id = :terrariumId")
    fun observeTerrariumById(terrariumId: Long): Flow<TerrariumEntity?>

    @Query("DELETE FROM terrariums WHERE id = :terrariumId")
    suspend fun deleteTerrariumById(terrariumId: Long)
}
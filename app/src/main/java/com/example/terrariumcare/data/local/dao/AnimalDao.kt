package com.example.terrariumcare.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.terrariumcare.data.local.entities.AnimalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {

    @Query("SELECT * FROM animals")
    fun getAllAnimals(): Flow<List<AnimalEntity>>

    @Query("SELECT * FROM animals WHERE terrariumId = :terrariumId")
    fun getAnimalsForTerrarium(terrariumId: Long): Flow<List<AnimalEntity>>

    @Query("SELECT * FROM animals WHERE id = :animalId")
    suspend fun getAnimalById(animalId: Long): AnimalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: AnimalEntity)

    @Update
    suspend fun updateAnimal(animal: AnimalEntity)

    @Delete
    suspend fun deleteAnimal(animal: AnimalEntity)
}
package com.example.terrariumcare.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "animals",
    foreignKeys = [
        ForeignKey(
            entity = TerrariumEntity::class,
            parentColumns = ["id"],
            childColumns = ["terrariumId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("terrariumId")]
)
data class AnimalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val terrariumId: Long,
    val name: String,
    val species: String,
    val birthDate: LocalDate,
    val currentWeightGrams: Double
)
package com.example.terrariumcare.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terrariums")
data class TerrariumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val dimensions: String,
    val targetTemperature: Double,
    val targetHumidity: Int
)
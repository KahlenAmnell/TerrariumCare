package com.example.terrariumcare.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terrariums")
data class TerrariumEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val habitatType: String,
    val location: String,
    val width: Int,
    val depth: Int,
    val height: Int,
    val photoUri: String? = null
)
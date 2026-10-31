package com.example.terrariumcare.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.terrariumcare.data.local.enums.CareEventType
import com.example.terrariumcare.data.local.enums.EntryType

@Entity(
    tableName = "log_entries",
    foreignKeys = [
        ForeignKey(
            entity = AnimalEntity::class,
            parentColumns = ["id"],
            childColumns = ["animalId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("animalId")]
)
data class LogEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val animalId: Long,
    val timestamp: Long,
    val entryType: EntryType,

    val baskingTemp: Double? = null,
    val coolTemp: Double? = null,
    val humidity: Int? = null,

    val careEventType: CareEventType? = null,
    val description: String? = null
)

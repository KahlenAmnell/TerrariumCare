package com.example.terrariumcare.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.terrariumcare.data.local.enums.CareEventType
import com.example.terrariumcare.data.local.enums.EntryType

@Entity(tableName = "log_entries")
data class LogEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val parentId: Long,
    val parentType: String,
    val eventType: String,
    val date: String,
    val time: String,

    val foodType: String? = null,
    val amountOffered: Double? = null,
    val appetite: String? = null,
    val notes: String? = null,
    val reminderEnabled: Boolean = false,

    val measuredTemperature: Double? = null,
    val measuredHumidity: Int? = null
)

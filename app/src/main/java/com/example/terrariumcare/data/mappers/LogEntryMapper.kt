package com.example.terrariumcare.data.mappers

import com.example.terrariumcare.data.local.entities.LogEntryEntity
import com.example.terrariumcare.data.local.enums.CareEventType
import com.example.terrariumcare.data.local.enums.EntryType
import com.example.terrariumcare.domain.model.LogEntry

fun LogEntryEntity.toDomainModel(): LogEntry {
    return when (eventType) {
        EntryType.ENVIRONMENT.name -> LogEntry.Environment(
            id = id,
            animalId = parentId,
            timestamp = 0L,
            baskingTemp = measuredTemperature,
            coolTemp = null,
            humidity = measuredHumidity
        )
        EntryType.CARE.name -> LogEntry.Care(
            id = id,
            animalId = parentId,
            timestamp = 0L,
            eventType = CareEventType.OTHER,
            description = notes
        )
        EntryType.NOTE.name -> LogEntry.Note(
            id = id,
            animalId = parentId,
            timestamp = 0L,
            description = notes ?: ""
        )
        else -> {
            val careEvent = try {
                CareEventType.valueOf(eventType)
            } catch (e: Exception) {
                null
            }
            if (careEvent != null) {
                LogEntry.Care(
                    id = id,
                    animalId = parentId,
                    timestamp = 0L,
                    eventType = careEvent,
                    description = notes
                )
            } else {
                LogEntry.Note(
                    id = id,
                    animalId = parentId,
                    timestamp = 0L,
                    description = notes ?: ""
                )
            }
        }
    }
}

fun LogEntry.toEntity(): LogEntryEntity {
    return when (this) {
        is LogEntry.Environment -> LogEntryEntity(
            id = id,
            parentId = animalId,
            parentType = "ANIMAL",
            eventType = EntryType.ENVIRONMENT.name,
            date = "",
            time = "",
            measuredTemperature = baskingTemp,
            measuredHumidity = humidity
        )
        is LogEntry.Care -> LogEntryEntity(
            id = id,
            parentId = animalId,
            parentType = "ANIMAL",
            eventType = eventType.name,
            date = "",
            time = "",
            notes = description
        )
        is LogEntry.Note -> LogEntryEntity(
            id = id,
            parentId = animalId,
            parentType = "ANIMAL",
            eventType = EntryType.NOTE.name,
            date = "",
            time = "",
            notes = description
        )
    }
}

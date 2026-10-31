package com.example.terrariumcare.data.mappers

import com.example.terrariumcare.data.local.entities.LogEntryEntity
import com.example.terrariumcare.data.local.enums.CareEventType
import com.example.terrariumcare.data.local.enums.EntryType
import com.example.terrariumcare.domain.model.LogEntry

fun LogEntryEntity.toDomainModel(): LogEntry {
    return when (entryType) {
        EntryType.ENVIRONMENT -> LogEntry.Environment(
            id = id,
            animalId = animalId,
            timestamp = timestamp,
            baskingTemp = baskingTemp,
            coolTemp = coolTemp,
            humidity = humidity
        )
        EntryType.CARE -> LogEntry.Care(
            id = id,
            animalId = animalId,
            timestamp = timestamp,
            eventType = careEventType ?: CareEventType.OTHER,
            description = description
        )
        EntryType.NOTE -> LogEntry.Note(
            id = id,
            animalId = animalId,
            timestamp = timestamp,
            description = description ?: ""
        )
    }
}

fun LogEntry.toEntity(): LogEntryEntity {
    return when (this) {
        is LogEntry.Environment -> LogEntryEntity(
            id = id,
            animalId = animalId,
            timestamp = timestamp,
            entryType = EntryType.ENVIRONMENT,
            baskingTemp = baskingTemp,
            coolTemp = coolTemp,
            humidity = humidity
        )
        is LogEntry.Care -> LogEntryEntity(
            id = id,
            animalId = animalId,
            timestamp = timestamp,
            entryType = EntryType.CARE,
            careEventType = eventType,
            description = description
        )
        is LogEntry.Note -> LogEntryEntity(
            id = id,
            animalId = animalId,
            timestamp = timestamp,
            entryType = EntryType.NOTE,
            description = description
        )
    }
}

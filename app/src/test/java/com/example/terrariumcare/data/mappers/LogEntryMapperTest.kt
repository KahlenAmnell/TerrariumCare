package com.example.terrariumcare.data.mappers

import com.example.terrariumcare.data.local.entities.LogEntryEntity
import com.example.terrariumcare.data.local.enums.CareEventType
import com.example.terrariumcare.data.local.enums.EntryType
import com.example.terrariumcare.domain.model.LogEntry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LogEntryMapperTest {

    @Test
    fun environmentEntityToDomain_mapsCorrectly() {
        val entity = LogEntryEntity(
            id = 1L,
            parentId = 10L,
            parentType = "ANIMAL",
            eventType = EntryType.ENVIRONMENT.name,
            date = "2023-05-15",
            time = "14:00",
            measuredTemperature = 32.5,
            measuredHumidity = 60
        )

        val domain = entity.toDomainModel()

        assertTrue(domain is LogEntry.Environment)
        val env = domain as LogEntry.Environment
        assertEquals(1L, env.id)
        assertEquals(10L, env.animalId)
        assertEquals(32.5, env.baskingTemp!!, 0.01)
        assertEquals(60, env.humidity)
    }

    @Test
    fun environmentDomainToEntity_mapsCorrectly() {
        val domain = LogEntry.Environment(
            id = 1L,
            animalId = 10L,
            timestamp = 1000000L,
            baskingTemp = 32.5,
            coolTemp = 24.0,
            humidity = 60
        )

        val entity = domain.toEntity()

        assertEquals(1L, entity.id)
        assertEquals(10L, entity.parentId)
        assertEquals("ANIMAL", entity.parentType)
        assertEquals(EntryType.ENVIRONMENT.name, entity.eventType)
        assertEquals(32.5, entity.measuredTemperature!!, 0.01)
        assertEquals(60, entity.measuredHumidity)
    }

    @Test
    fun careEntityToDomain_mapsCorrectly() {
        val entity = LogEntryEntity(
            id = 2L,
            parentId = 10L,
            parentType = "ANIMAL",
            eventType = CareEventType.FEEDING.name,
            date = "2023-05-15",
            time = "14:00",
            notes = "Ate 2 crickets"
        )

        val domain = entity.toDomainModel()

        assertTrue(domain is LogEntry.Care)
        val care = domain as LogEntry.Care
        assertEquals(2L, care.id)
        assertEquals(10L, care.animalId)
        assertEquals(CareEventType.FEEDING, care.eventType)
        assertEquals("Ate 2 crickets", care.description)
    }

    @Test
    fun careDomainToEntity_mapsCorrectly() {
        val domain = LogEntry.Care(
            id = 2L,
            animalId = 10L,
            timestamp = 2000000L,
            eventType = CareEventType.FEEDING,
            description = "Ate 2 crickets"
        )

        val entity = domain.toEntity()

        assertEquals(2L, entity.id)
        assertEquals(10L, entity.parentId)
        assertEquals("ANIMAL", entity.parentType)
        assertEquals("FEEDING", entity.eventType)
        assertEquals("Ate 2 crickets", entity.notes)
    }

    @Test
    fun noteEntityToDomain_mapsCorrectly() {
        val entity = LogEntryEntity(
            id = 3L,
            parentId = 10L,
            parentType = "ANIMAL",
            eventType = EntryType.NOTE.name,
            date = "2023-05-15",
            time = "14:00",
            notes = "Behavior looks active today"
        )

        val domain = entity.toDomainModel()

        assertTrue(domain is LogEntry.Note)
        val note = domain as LogEntry.Note
        assertEquals(3L, note.id)
        assertEquals(10L, note.animalId)
        assertEquals("Behavior looks active today", note.description)
    }

    @Test
    fun noteDomainToEntity_mapsCorrectly() {
        val domain = LogEntry.Note(
            id = 3L,
            animalId = 10L,
            timestamp = 3000000L,
            description = "Behavior looks active today"
        )

        val entity = domain.toEntity()

        assertEquals(3L, entity.id)
        assertEquals(10L, entity.parentId)
        assertEquals("ANIMAL", entity.parentType)
        assertEquals(EntryType.NOTE.name, entity.eventType)
        assertEquals("Behavior looks active today", entity.notes)
    }
}

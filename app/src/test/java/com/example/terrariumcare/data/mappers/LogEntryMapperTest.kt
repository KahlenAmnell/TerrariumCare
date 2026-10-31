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
            animalId = 10L,
            timestamp = 1000000L,
            entryType = EntryType.ENVIRONMENT,
            baskingTemp = 32.5,
            coolTemp = 24.0,
            humidity = 60
        )

        val domain = entity.toDomainModel()

        assertTrue(domain is LogEntry.Environment)
        val env = domain as LogEntry.Environment
        assertEquals(1L, env.id)
        assertEquals(10L, env.animalId)
        assertEquals(1000000L, env.timestamp)
        assertEquals(32.5, env.baskingTemp!!, 0.01)
        assertEquals(24.0, env.coolTemp!!, 0.01)
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
        assertEquals(10L, entity.animalId)
        assertEquals(1000000L, entity.timestamp)
        assertEquals(EntryType.ENVIRONMENT, entity.entryType)
        assertEquals(32.5, entity.baskingTemp!!, 0.01)
        assertEquals(24.0, entity.coolTemp!!, 0.01)
        assertEquals(60, entity.humidity)
    }

    @Test
    fun careEntityToDomain_mapsCorrectly() {
        val entity = LogEntryEntity(
            id = 2L,
            animalId = 10L,
            timestamp = 2000000L,
            entryType = EntryType.CARE,
            careEventType = CareEventType.FEEDING,
            description = "Ate 2 crickets"
        )

        val domain = entity.toDomainModel()

        assertTrue(domain is LogEntry.Care)
        val care = domain as LogEntry.Care
        assertEquals(2L, care.id)
        assertEquals(10L, care.animalId)
        assertEquals(2000000L, care.timestamp)
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
        assertEquals(10L, entity.animalId)
        assertEquals(2000000L, entity.timestamp)
        assertEquals(EntryType.CARE, entity.entryType)
        assertEquals(CareEventType.FEEDING, entity.careEventType)
        assertEquals("Ate 2 crickets", entity.description)
    }

    @Test
    fun noteEntityToDomain_mapsCorrectly() {
        val entity = LogEntryEntity(
            id = 3L,
            animalId = 10L,
            timestamp = 3000000L,
            entryType = EntryType.NOTE,
            description = "Behavior looks active today"
        )

        val domain = entity.toDomainModel()

        assertTrue(domain is LogEntry.Note)
        val note = domain as LogEntry.Note
        assertEquals(3L, note.id)
        assertEquals(10L, note.animalId)
        assertEquals(3000000L, note.timestamp)
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
        assertEquals(10L, entity.animalId)
        assertEquals(3000000L, entity.timestamp)
        assertEquals(EntryType.NOTE, entity.entryType)
        assertEquals("Behavior looks active today", entity.description)
    }
}

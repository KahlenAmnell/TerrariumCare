package com.example.terrariumcare.domain.model

import com.example.terrariumcare.data.local.enums.CareEventType

sealed class LogEntry {
    abstract val id: Long
    abstract val animalId: Long
    abstract val timestamp: Long

    data class Environment(
        override val id: Long = 0L,
        override val animalId: Long,
        override val timestamp: Long,
        val baskingTemp: Double? = null,
        val coolTemp: Double? = null,
        val humidity: Int? = null
    ) : LogEntry()

    data class Care(
        override val id: Long = 0L,
        override val animalId: Long,
        override val timestamp: Long,
        val eventType: CareEventType,
        val description: String? = null
    ) : LogEntry()

    data class Note(
        override val id: Long = 0L,
        override val animalId: Long,
        override val timestamp: Long,
        val description: String
    ) : LogEntry()
}

package com.example.terrariumcare.data.local.converters

import com.example.terrariumcare.data.local.enums.CareEventType
import com.example.terrariumcare.data.local.enums.EntryType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class EnumConvertersTest {

    private val converters = EnumConverters()


    @Test
    fun fromEntryType_convertsEnumToString() {
        val result = converters.fromEntryType(EntryType.ENVIRONMENT)
        assertEquals("ENVIRONMENT", result)
    }

    @Test
    fun fromEntryType_returnsNullForNullEnum() {
        val result = converters.fromEntryType(null)
        assertNull(result)
    }

    @Test
    fun toEntryType_convertsStringToEnum() {
        val result = converters.toEntryType("CARE")
        assertEquals(EntryType.CARE, result)
    }

    @Test
    fun toEntryType_returnsNullForInvalidString() {
        val result = converters.toEntryType("INVALID_TYPE")
        assertNull(result)
    }

    @Test
    fun toEntryType_returnsNullForNullString() {
        val result = converters.toEntryType(null)
        assertNull(result)
    }

    // --- CareEventType Tests ---

    @Test
    fun fromCareEventType_convertsEnumToString() {
        val result = converters.fromCareEventType(CareEventType.FEEDING)
        assertEquals("FEEDING", result)
    }

    @Test
    fun fromCareEventType_returnsNullForNullEnum() {
        val result = converters.fromCareEventType(null)
        assertNull(result)
    }

    @Test
    fun toCareEventType_convertsStringToEnum() {
        val result = converters.toCareEventType("SHEDDING")
        assertEquals(CareEventType.SHEDDING, result)
    }

    @Test
    fun toCareEventType_returnsNullForInvalidString() {
        val result = converters.toCareEventType("UNKNOWN_EVENT")
        assertNull(result)
    }

    @Test
    fun toCareEventType_returnsNullForNullString() {
        val result = converters.toCareEventType(null)
        assertNull(result)
    }
}

package com.example.terrariumcare.data.local.converters

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.LocalDate

class DateConvertersTest {

    private val converters = DateConverters()

    @Test
    fun fromLocalDate_convertsLocalDateToString() {
        val date = LocalDate.of(2023, 5, 15)
        val result = converters.fromLocalDate(date)
        assertEquals("2023-05-15", result)
    }

    @Test
    fun fromLocalDate_returnsNullForNullDate() {
        val result = converters.fromLocalDate(null)
        assertNull(result)
    }

    @Test
    fun toLocalDate_convertsStringToLocalDate() {
        val dateString = "2023-05-15"
        val result = converters.toLocalDate(dateString)
        assertEquals(LocalDate.of(2023, 5, 15), result)
    }

    @Test
    fun toLocalDate_returnsNullForNullString() {
        val result = converters.toLocalDate(null)
        assertNull(result)
    }
}

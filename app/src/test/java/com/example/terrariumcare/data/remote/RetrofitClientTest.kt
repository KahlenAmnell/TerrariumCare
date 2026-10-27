package com.example.terrariumcare.data.remote

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RetrofitClientTest {
    @Test
    fun testFetchSpecies() = runBlocking {
        val speciesList = RetrofitClient.speciesApi.getSpecies()
        assertNotNull(speciesList)
        assertTrue(speciesList.isNotEmpty())
    }
}

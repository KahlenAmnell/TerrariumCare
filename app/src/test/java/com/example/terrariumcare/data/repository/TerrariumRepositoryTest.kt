package com.example.terrariumcare.data.repository

import com.example.terrariumcare.data.local.dao.TerrariumDao
import com.example.terrariumcare.data.local.entities.TerrariumEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class TerrariumRepositoryTest {

    private lateinit var fakeDao: FakeTerrariumDao
    private lateinit var repository: TerrariumRepository

    @Before
    fun setUp() {
        fakeDao = FakeTerrariumDao()
        repository = TerrariumRepository(fakeDao)
    }

    @Test
    fun insertTerrarium_addsTerrariumToRepository() = runBlocking {
        val terrarium = TerrariumEntity(
            id = 1L,
            name = "Tropikalne",
            habitatType = "Tropical",
            location = "Salon",
            width = 60,
            depth = 45,
            height = 90
        )

        val id = repository.insertTerrarium(terrarium)
        assertEquals(1L, id)

        val result = repository.getTerrariumById(1L)
        assertEquals(terrarium, result)
    }

    @Test
    fun allTerrariums_emitsListOfTerrariums() = runBlocking {
        val terrarium1 = TerrariumEntity(id = 1L, name = "A", habitatType = "T", location = "L", width = 1, depth = 1, height = 1)
        val terrarium2 = TerrariumEntity(id = 2L, name = "B", habitatType = "D", location = "L", width = 1, depth = 1, height = 1)

        repository.insertTerrarium(terrarium1)
        repository.insertTerrarium(terrarium2)

        val list = repository.allTerrariums.first()
        assertEquals(2, list.size)
        assertEquals("A", list[0].name)
        assertEquals("B", list[1].name)
    }

    @Test
    fun deleteTerrarium_removesTerrariumFromRepository() = runBlocking {
        val terrarium = TerrariumEntity(id = 1L, name = "Test", habitatType = "T", location = "L", width = 1, depth = 1, height = 1)
        repository.insertTerrarium(terrarium)

        repository.deleteTerrarium(terrarium)

        val result = repository.getTerrariumById(1L)
        assertNull(result)
    }

    @Test
    fun deleteTerrariumById_removesTerrariumFromRepository() = runBlocking {
        val terrarium = TerrariumEntity(id = 1L, name = "Test", habitatType = "T", location = "L", width = 1, depth = 1, height = 1)
        repository.insertTerrarium(terrarium)

        repository.deleteTerrariumById(1L)

        val result = repository.getTerrariumById(1L)
        assertNull(result)
    }
}

private class FakeTerrariumDao : TerrariumDao {
    private val terrariums = MutableStateFlow<List<TerrariumEntity>>(emptyList())
    private var nextId = 1L

    override fun getAllTerrariums(): Flow<List<TerrariumEntity>> = terrariums

    override suspend fun getTerrariumById(terrariumId: Long): TerrariumEntity? {
        return terrariums.value.find { it.id == terrariumId }
    }

    override fun observeTerrariumById(terrariumId: Long): Flow<TerrariumEntity?> {
        return terrariums.map { list -> list.find { it.id == terrariumId } }
    }

    override suspend fun insertTerrarium(terrarium: TerrariumEntity): Long {
        val idToUse = if (terrarium.id == 0L) nextId++ else terrarium.id
        val entityWithId = terrarium.copy(id = idToUse)
        terrariums.value = terrariums.value.filterNot { it.id == idToUse } + entityWithId
        return idToUse
    }

    override suspend fun updateTerrarium(terrarium: TerrariumEntity) {
        terrariums.value = terrariums.value.map { if (it.id == terrarium.id) terrarium else it }
    }

    override suspend fun deleteTerrarium(terrarium: TerrariumEntity) {
        terrariums.value = terrariums.value.filterNot { it.id == terrarium.id }
    }

    override suspend fun deleteTerrariumById(terrariumId: Long) {
        terrariums.value = terrariums.value.filterNot { it.id == terrariumId }
    }
}

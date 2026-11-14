package com.example.terrariumcare.ui.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.terrariumcare.data.local.dao.TerrariumDao
import com.example.terrariumcare.data.local.entities.TerrariumEntity
import com.example.terrariumcare.data.repository.TerrariumRepository
import com.example.terrariumcare.utils.ImageStorageManagerImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AddTerrariumViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var fakeDao: FakeTerrariumDaoForViewModel
    private lateinit var repository: TerrariumRepository
    private lateinit var viewModel: AddTerrariumViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val context = ApplicationProvider.getApplicationContext<Context>()
        val imageStorageManager = ImageStorageManagerImpl(context)
        fakeDao = FakeTerrariumDaoForViewModel()
        repository = TerrariumRepository(fakeDao)
        viewModel = AddTerrariumViewModel(repository, imageStorageManager)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun saveTerrarium_withBlankName_setsErrorTrue() {
        var successCalled = false
        viewModel.name.value = ""

        viewModel.saveTerrarium { successCalled = true }

        assertTrue(viewModel.isError.value)
        assertFalse(successCalled)
    }

    @Test
    fun saveTerrarium_withValidName_savesAndTriggersSuccess() = runBlocking {
        var successCalled = false
        viewModel.onNameChange("Tropikalne 60x45")
        viewModel.onHabitatTypeChange("Tropical")
        viewModel.onLocationChange("Salon")
        viewModel.onWidthChange("60")
        viewModel.onDepthChange("45")
        viewModel.onHeightChange("90")

        viewModel.saveTerrarium { successCalled = true }

        assertFalse(viewModel.isError.value)
        assertTrue(successCalled)

        val list = repository.allTerrariums.first()
        assertEquals(1, list.size)
        assertEquals("Tropikalne 60x45", list[0].name)
    }

    @Test
    fun onNameChange_clearsErrorStateWhenValidTextEntered() {
        viewModel.saveTerrarium {}
        assertTrue(viewModel.isError.value)

        viewModel.onNameChange("Nowe terrarium")
        assertFalse(viewModel.isError.value)
    }
}

private class FakeTerrariumDaoForViewModel : TerrariumDao {
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

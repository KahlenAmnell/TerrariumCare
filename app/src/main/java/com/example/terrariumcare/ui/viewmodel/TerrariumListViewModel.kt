package com.example.terrariumcare.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.terrariumcare.data.local.entities.TerrariumEntity
import com.example.terrariumcare.data.repository.TerrariumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TerrariumListViewModel @Inject constructor(
    private val repository: TerrariumRepository
) : ViewModel() {

    val terrariums: StateFlow<List<TerrariumEntity>> = repository.allTerrariums
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTerrarium(
        name: String,
        habitatType: String,
        location: String,
        width: Int,
        depth: Int,
        height: Int,
        photoUri: String? = null
    ) {
        viewModelScope.launch {
            val newTerrarium = TerrariumEntity(
                name = name,
                habitatType = habitatType,
                location = location,
                width = width,
                depth = depth,
                height = height,
                photoUri = photoUri
            )
            repository.insertTerrarium(newTerrarium)
        }
    }

    fun deleteTerrarium(terrarium: TerrariumEntity) {
        viewModelScope.launch {
            repository.deleteTerrarium(terrarium)
        }
    }
}

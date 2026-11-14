package com.example.terrariumcare.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.terrariumcare.data.local.entities.TerrariumEntity
import com.example.terrariumcare.data.repository.TerrariumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTerrariumViewModel @Inject constructor(
    private val repository: TerrariumRepository
) : ViewModel() {

    val name = MutableStateFlow("")
    val habitatType = MutableStateFlow("Tropical")
    val location = MutableStateFlow("")
    val width = MutableStateFlow("")
    val depth = MutableStateFlow("")
    val height = MutableStateFlow("")
    val photoUri = MutableStateFlow<String?>(null)

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    fun onNameChange(newValue: String) {
        name.value = newValue
        if (_isError.value && newValue.isNotBlank()) {
            _isError.value = false
        }
    }

    fun onHabitatTypeChange(newValue: String) {
        habitatType.value = newValue
    }

    fun onLocationChange(newValue: String) {
        location.value = newValue
    }

    fun onWidthChange(newValue: String) {
        width.value = newValue
    }

    fun onDepthChange(newValue: String) {
        depth.value = newValue
    }

    fun onHeightChange(newValue: String) {
        height.value = newValue
    }

    fun onPhotoUriChange(newValue: String?) {
        photoUri.value = newValue
    }

    fun saveTerrarium(onSuccess: () -> Unit) {
        if (name.value.isBlank()) {
            _isError.value = true
            return
        }

        _isError.value = false

        viewModelScope.launch {
            val entity = TerrariumEntity(
                name = name.value.trim(),
                habitatType = habitatType.value,
                location = location.value.trim(),
                width = width.value.toIntOrNull() ?: 0,
                depth = depth.value.toIntOrNull() ?: 0,
                height = height.value.toIntOrNull() ?: 0,
                photoUri = photoUri.value
            )
            repository.insertTerrarium(entity)
            onSuccess()
        }
    }
}

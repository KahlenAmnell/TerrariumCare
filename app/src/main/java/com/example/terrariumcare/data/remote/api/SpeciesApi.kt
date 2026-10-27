package com.example.terrariumcare.data.remote.api


import com.example.terrariumcare.data.remote.dto.SpeciesDto
import retrofit2.http.GET

interface SpeciesApi {
    @GET("species")
    suspend fun getSpecies(): List<SpeciesDto>
}
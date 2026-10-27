package com.example.terrariumcare.data.remote

import com.example.terrariumcare.data.remote.api.SpeciesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://gist.githubusercontent.com/KahlenAmnell/45ea2a7b8d8adef0028bbeeaab3bca61/raw/30eed1009d3cca8d4c58895d40f25ea5f8ff2e1d/"

    val speciesApi: SpeciesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpeciesApi::class.java)
    }
}

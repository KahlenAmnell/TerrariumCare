package com.example.terrariumcare.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SpeciesDto(
    val id: Int,
    val name: String,
    @SerializedName("name_pl") val namePl: String,
    @SerializedName("scientific_name") val scientificName: String,
    @SerializedName("source_url") val sourceUrl: String,
    @SerializedName("min_enclosure_size") val minEnclosureSize: String,
    @SerializedName("basking_temp_c") val baskingTempC: Double,
    @SerializedName("warm_side_temp_c") val warmSideTempC: Double,
    @SerializedName("cool_side_temp_c") val coolSideTempC: Double,
    @SerializedName("night_temp_c") val nightTempC: Double,
    @SerializedName("humidity_min_percent") val humidityMinPercent: Int,
    @SerializedName("humidity_max_percent") val humidityMaxPercent: Int,
    @SerializedName("ferguson_zone_uvb") val fergusonZoneUvb: String,
    @SerializedName("diet_type") val dietType: String,
    val description: String
)
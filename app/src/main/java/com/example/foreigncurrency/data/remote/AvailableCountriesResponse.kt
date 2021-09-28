package com.example.foreigncurrency.data.remote

import com.squareup.moshi.Json

data class AvailableCountriesResponse(
    @field:Json(name = "success") val success: Boolean,
    @field:Json(name = "symbols") val symbols: Map<String, String>
)

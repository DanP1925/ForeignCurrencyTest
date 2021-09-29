package com.example.foreigncurrency.data.remote

data class LatestRatesResponse(
    val success: Boolean,
    val symbols: Map<String, Double>
)

package com.example.foreigncurrency.data

import kotlinx.coroutines.flow.Flow

interface CountryRepository {

    suspend fun fetchCountries(): Flow<List<Country>>
}
package com.example.foreigncurrency.data

import kotlinx.coroutines.flow.Flow

interface CountryDataSource {

    suspend fun getCountries(): Flow<List<Country>>

}
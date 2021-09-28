package com.example.foreigncurrency.data

import kotlinx.coroutines.flow.Flow

class CountryRepository(
    private val remoteDataSource: CountryDataSource
) {

    suspend fun getCountries(): Flow<List<Country>> {
        return remoteDataSource.getCountries()
    }

}
package com.example.foreigncurrency.data

class CountryRepository(
    private val remoteDataSource: CountryDataSource
) {

    fun getCountries(): List<Country> {
        return remoteDataSource.getCountries()
    }

}
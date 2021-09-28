package com.example.foreigncurrency.data

class CountryRepository(
    remoteDataSource: CountryDataSource
) {

    fun getCountries(): List<Country> {
        //TODO: Get countries from different data sets
        return emptyList()
    }

}
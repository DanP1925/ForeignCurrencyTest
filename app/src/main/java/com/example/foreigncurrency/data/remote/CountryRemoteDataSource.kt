package com.example.foreigncurrency.data.remote

import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryDataSource

class CountryRemoteDataSource : CountryDataSource {
    override fun getCountries(): List<Country> {
        //TODO: Replace with request to Fixer API
        return emptyList()
    }
}
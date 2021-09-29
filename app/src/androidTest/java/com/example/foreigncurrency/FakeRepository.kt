package com.example.foreigncurrency

import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryRepository
import com.example.foreigncurrency.di.FakeAppModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : CountryRepository {

    private val FAKE_COUNTRIES = listOf(
        Country("Armenian Dram", "AMD"),
        Country("Europe", "EUR")
    )

    override suspend fun fetchCountries(): Flow<List<Country>> = flow {
        emit(FAKE_COUNTRIES)
    }

}
package com.example.foreigncurrency.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultCountryRepository @Inject constructor(
    private val remoteDataSource: CountryDataSource
) : CountryRepository {

    override suspend fun fetchCountries(): Flow<List<Country>> {
        return remoteDataSource.fetchCountries()
    }

    override suspend fun fetchExchangeRates(currency: String): Flow<List<CurrencyExchangeRate>> {
        return remoteDataSource.fetchExchangeRates(currency)
    }

}
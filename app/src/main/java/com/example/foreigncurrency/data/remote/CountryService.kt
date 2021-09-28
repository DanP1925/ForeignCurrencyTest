package com.example.foreigncurrency.data.remote

import retrofit2.http.POST
import retrofit2.http.Query

interface CountryService {

    @POST("symbols")
    suspend fun fetchAvailableCountries(@Query("access_key") key: String): AvailableCountriesResponse

}
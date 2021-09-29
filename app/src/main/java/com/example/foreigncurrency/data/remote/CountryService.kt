package com.example.foreigncurrency.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {

    @GET("symbols")
    suspend fun fetchAvailableCountries(@Query("access_key") key: String): AvailableCountriesResponse


    // There is an issue with Moshi AND Map<String, Double> and that is why I'm returning a ResponseBody
    @GET("latest")
    suspend fun fetchLatestRates(
        @Query("access_key") key: String,
        @Query("base") baseCurrency: String
    ) : ResponseBody

}
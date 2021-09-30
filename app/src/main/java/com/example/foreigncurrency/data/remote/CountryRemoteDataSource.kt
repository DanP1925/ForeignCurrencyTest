package com.example.foreigncurrency.data.remote

import android.util.Base64
import android.util.Base64.decode
import com.example.foreigncurrency.BuildConfig
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryDataSource
import com.example.foreigncurrency.data.CurrencyExchangeRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class CountryRemoteDataSource(private val countryService: CountryService) : CountryDataSource {
    private fun decodeAK(): String {
        val data: ByteArray = decode(BuildConfig.AK, Base64.DEFAULT)
        var param = ""
        try {
            param = String(data, Charset.defaultCharset())
        } catch (e: UnsupportedEncodingException) {
            throw e
        }
        return param
    }

    @ExperimentalCoroutinesApi
    override suspend fun fetchCountries(): Flow<List<Country>> = flow {
        val response = countryService.fetchAvailableCountries(decodeAK())
        if (response.success) {
            val countries = response.symbols.map { (key, value) ->
                Country(value, key)
            }
            emit(countries)
        } else {
            throw RuntimeException()
        }
    }.flowOn(Dispatchers.IO)

    @ExperimentalCoroutinesApi
    override suspend fun fetchExchangeRates(currency: String): Flow<List<CurrencyExchangeRate>> =
        flow {
            // There is an issue with Moshi AND Map<String, Double> and that is why I'm returning a ResponseBody
            val responseBody: ResponseBody = countryService.fetchLatestRates(decodeAK(), currency)
            val latestRatesResponseJSON = JSONObject(responseBody.string())
            val symbols = HashMap<String, Double>()
            val ratesJSON = latestRatesResponseJSON.getJSONObject("rates")
            for (key in ratesJSON.keys()) {
                symbols[key] = ratesJSON.getDouble(key)
            }
            val response = LatestRatesResponse(
                latestRatesResponseJSON.getBoolean("success"),
                symbols
            )

            if (response.success) {
                val rates = response.symbols.map { (key, value) ->
                    CurrencyExchangeRate(key, value)
                }
                emit(rates)
            } else {
                throw RuntimeException()
            }
        }.flowOn(Dispatchers.IO)
}

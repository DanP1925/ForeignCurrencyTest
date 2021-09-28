package com.example.foreigncurrency.data.remote

import android.util.Base64
import android.util.Base64.decode
import com.example.foreigncurrency.BuildConfig
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import java.io.UnsupportedEncodingException
import java.lang.RuntimeException
import java.nio.charset.Charset

class CountryRemoteDataSource(private val countryService: CountryService) : CountryDataSource {
    override suspend fun getCountries(): Flow<List<Country>> = flow {
        val data: ByteArray = decode(BuildConfig.AK, Base64.DEFAULT)
        var param = ""
        try {
            param = String(data, Charset.defaultCharset())
        } catch (e: UnsupportedEncodingException) {
            throw e
        }
        val response = countryService.fetchAvailableCountries(param)
        if (response.success) {
            val countries = response.symbols.map { (key, value) ->
                Country(value, key)
            }
            emit(countries)
        } else {
            throw RuntimeException()
        }
    }.flowOn(Dispatchers.IO)
}

package com.example.foreigncurrency.supportedcountries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SupportedCountriesViewModel @Inject constructor(
    private val defaultCountryRepository: CountryRepository
) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        viewModelScope.launch {
            fetchCountries()
        }
    }

    suspend fun fetchCountries() {
        defaultCountryRepository.fetchCountries()
            .catch {

            }
            .collect { countries ->
                _countries.value = countries
            }
    }

}
package com.example.foreigncurrency.supportedcountries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SupportedCountriesViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    fun fetchCountries() {
        _countries.value = countryRepository.getCountries()
    }

}
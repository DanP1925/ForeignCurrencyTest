package com.example.foreigncurrency.supportedcountries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryRepository

class SupportedCountriesViewModel(private val countryRepository: CountryRepository) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    fun getCountries() {

    }

}
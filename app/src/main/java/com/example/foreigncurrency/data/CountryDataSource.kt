package com.example.foreigncurrency.data

interface CountryDataSource {

    fun getCountries(): List<Country>

}
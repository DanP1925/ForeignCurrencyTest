package com.example.foreigncurrency.currencyconversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foreigncurrency.data.CountryRepository
import com.example.foreigncurrency.data.CurrencyEquivalent
import com.example.foreigncurrency.data.CurrencyExchangeRate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(
    private val defaultCountryRepository: CountryRepository
) : ViewModel() {

    lateinit var exchangeRates: List<CurrencyExchangeRate>

    private val _equivalents = MutableLiveData<List<CurrencyEquivalent>>()
    val equivalents: LiveData<List<CurrencyEquivalent>> = _equivalents

    fun fetchExchangeRates(currencyName: String) {
    }

    fun obtainEquivalents(amount: Double) {
        _equivalents.value = exchangeRates.map { exchangeRate ->
            CurrencyEquivalent(
                exchangeRate.currencySymbol, exchangeRate.exchangeRate * amount
            )
        }
    }

}
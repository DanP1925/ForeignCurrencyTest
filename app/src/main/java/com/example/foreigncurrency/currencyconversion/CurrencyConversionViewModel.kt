package com.example.foreigncurrency.currencyconversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foreigncurrency.data.CountryRepository
import com.example.foreigncurrency.data.CurrencyEquivalent
import com.example.foreigncurrency.data.CurrencyExchangeRate
import com.example.foreigncurrency.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConversionViewModel @Inject constructor(
    private val defaultCountryRepository: CountryRepository
) : ViewModel() {

    var exchangeRates: List<CurrencyExchangeRate>? = null

    private val _equivalents = MutableLiveData<List<CurrencyEquivalent>>()
    val equivalents: LiveData<List<CurrencyEquivalent>> = _equivalents

    private val _showErrorEvent = MutableLiveData<Event<String?>>()
    val showErrorEvent: LiveData<Event<String?>> = _showErrorEvent

    @ExperimentalCoroutinesApi
    fun fetchExchangeRates(currencyName: String) {
        viewModelScope.launch {
            defaultCountryRepository.fetchExchangeRates(currencyName)
                .catch { exception ->
                    _showErrorEvent.value = Event(exception.message)
                }.collect {
                    exchangeRates = it
                }
        }
    }

    fun obtainEquivalents(amount: Double) {
        exchangeRates?.let { exchangeRates ->
            _equivalents.value = exchangeRates.map { exchangeRate ->
                CurrencyEquivalent(
                    exchangeRate.currencySymbol, exchangeRate.exchangeRate * amount
                )
            }
        }
    }

}
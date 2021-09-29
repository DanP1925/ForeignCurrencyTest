package com.example.foreigncurrency.currencyconversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foreigncurrency.data.CurrencyEquivalent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyConversionViewModel @Inject constructor() : ViewModel() {

    private val _equivalents = MutableLiveData<List<CurrencyEquivalent>>()
    val equivalents: LiveData<List<CurrencyEquivalent>> = _equivalents

    fun fetchEquivalents(currencyName: String) {

    }

}
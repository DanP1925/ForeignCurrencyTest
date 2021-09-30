package com.example.foreigncurrency.supportedcountries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryRepository
import com.example.foreigncurrency.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _showErrorEvent = MutableLiveData<Event<Unit>>()
    val showErrorEvent: LiveData<Event<Unit>> = _showErrorEvent

    init {
        viewModelScope.launch {
            _loading.value = true
            fetchCountries()
        }
    }

    suspend fun fetchCountries() {
        defaultCountryRepository.fetchCountries()
            .catch {
                _showErrorEvent.value = Event(Unit)
            }
            .collect { countries ->
                _loading.value = false
                _countries.value = countries
            }
    }

}
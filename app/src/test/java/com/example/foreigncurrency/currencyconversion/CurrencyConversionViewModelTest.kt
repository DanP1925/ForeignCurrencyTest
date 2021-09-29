package com.example.foreigncurrency.currencyconversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foreigncurrency.data.CurrencyExchangeRate
import com.example.foreigncurrency.getOrAwaitValue
import com.example.foreigncurrency.observeForTesting
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CurrencyConversionViewModelTest {

    private lateinit var viewModel: CurrencyConversionViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        viewModel = CurrencyConversionViewModel()
    }

    private val EQUIVALENTS_SIZE = 2
    private val FAKE_EXCHANGE_RATES = listOf(
        CurrencyExchangeRate("USD",1.25),
        CurrencyExchangeRate("PER",4.0)
    )

    @Test
    fun obtainEquivalents_success() {
        //GIVEN
        viewModel.exchangeRates = FAKE_EXCHANGE_RATES

        //WHEN
        viewModel.obtainEquivalents(3.5)
        viewModel.equivalents.observeForTesting {
            val equivalents = viewModel.equivalents.getOrAwaitValue()

            //THEN
            assertThat(equivalents).hasSize(EQUIVALENTS_SIZE)
            assertThat(equivalents.get(1).equivalentAmount).isEqualTo(14)

        }

    }

}
package com.example.foreigncurrency.currencyconversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foreigncurrency.data.Country
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

    @ExperimentalCoroutinesApi
    @Test
    fun getConversions_success() {
        //GIVEN
        val currencyName = "EUR"

        //WHEN
        viewModel.fetchEquivalents(currencyName)
        viewModel.equivalents.observeForTesting {
            val equivalents = viewModel.equivalents.getOrAwaitValue()

            //THEN
            assertThat(equivalents).hasSize(EQUIVALENTS_SIZE)
            assertThat(equivalents.get(1).equivalentAmount).isEqualTo(14)

        }

    }

}
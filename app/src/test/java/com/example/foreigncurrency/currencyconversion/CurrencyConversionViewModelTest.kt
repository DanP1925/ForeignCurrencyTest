package com.example.foreigncurrency.currencyconversion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foreigncurrency.MainCoroutineRule
import com.example.foreigncurrency.data.CurrencyExchangeRate
import com.example.foreigncurrency.data.DefaultCountryRepository
import com.example.foreigncurrency.getOrAwaitValue
import com.example.foreigncurrency.observeForTesting
import com.example.foreigncurrency.util.Event
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.lang.RuntimeException

class CurrencyConversionViewModelTest {

    private lateinit var viewModel: CurrencyConversionViewModel
    private lateinit var fakeRepository: DefaultCountryRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        fakeRepository = Mockito.mock(DefaultCountryRepository::class.java)
        viewModel = CurrencyConversionViewModel(fakeRepository)
    }

    private val RATES_SIZE = 2
    private val FAKE_EXCHANGE_RATES = listOf(
        CurrencyExchangeRate("USD", 1.25),
        CurrencyExchangeRate("PER", 4.0)
    )

    @Test
    fun fetchExchangeRates_success() = mainCoroutineRule.runBlockingTest {
        //GIVEN
        val SELECTED_CURRENCY = "EUR"
        `when`(fakeRepository.fetchExchangeRates(SELECTED_CURRENCY)).thenReturn(flow {
            emit(FAKE_EXCHANGE_RATES)
        })

        //WHEN
        viewModel.fetchExchangeRates(SELECTED_CURRENCY)

        //THEN
        assertThat(viewModel.exchangeRates).hasSize(RATES_SIZE)
        assertThat(viewModel.exchangeRates?.get(1)?.currencySymbol).isEqualTo("PER")
    }

    @Test
    fun fetchExchangeRates_failure() = mainCoroutineRule.runBlockingTest {
        //GIVEN
        val SELECTED_CURRENCY = "EUR"
        val ERROR_MESSAGE = "base_currency_access_restricted"
        `when`(fakeRepository.fetchExchangeRates(SELECTED_CURRENCY)).thenReturn(flow {
            throw RuntimeException(ERROR_MESSAGE)
        })

        //WHEN
        viewModel.fetchExchangeRates(SELECTED_CURRENCY)

        //THEN
        viewModel.showErrorEvent.observeForTesting {
            val event = viewModel.showErrorEvent.getOrAwaitValue()

            //THEN
            assertThat(event.peekContent()).matches(ERROR_MESSAGE)
        }
    }

    @Test
    fun obtainEquivalents_success() {
        //GIVEN
        val AMOUNT_TO_CONVERT = 3.5
        val CONVERSION_RESULT = 14
        viewModel.exchangeRates = FAKE_EXCHANGE_RATES

        //WHEN
        viewModel.obtainEquivalents(AMOUNT_TO_CONVERT)
        viewModel.equivalents.observeForTesting {
            val equivalents = viewModel.equivalents.getOrAwaitValue()

            //THEN
            assertThat(equivalents).hasSize(RATES_SIZE)
            assertThat(equivalents.get(1).equivalentAmount).isEqualTo(CONVERSION_RESULT)
        }

    }

}
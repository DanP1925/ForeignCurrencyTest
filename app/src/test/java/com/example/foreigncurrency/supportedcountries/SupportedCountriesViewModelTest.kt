package com.example.foreigncurrency.supportedcountries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryRepository
import com.example.foreigncurrency.getOrAwaitValue
import com.example.foreigncurrency.observeForTesting
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SupportedCountriesViewModelTest {

    private lateinit var viewModel: SupportedCountriesViewModel
    private lateinit var fakeRepository: CountryRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val COUNTRIES_SIZE = 2
    private val FAKE_COUNTRIES = listOf(
        Country("AMD", "Armenian Dram"), Country("Europe", "EUR")
    )

    @Before
    fun setupViewModel() {
        fakeRepository = mock(CountryRepository::class.java)
        viewModel = SupportedCountriesViewModel(fakeRepository)
    }

    @Test
    fun getCountries_success() {
        //GIVEN
        `when`(fakeRepository.getCountries()).thenReturn(FAKE_COUNTRIES)

        //WHEN
        viewModel.getCountries()
        viewModel.countries.observeForTesting {
            val countries = viewModel.countries.getOrAwaitValue()

            //THEN
            assertThat(countries).hasSize(COUNTRIES_SIZE)
            assertThat(countries.get(1).currencySymbol).matches("EUR")
        }
    }

}
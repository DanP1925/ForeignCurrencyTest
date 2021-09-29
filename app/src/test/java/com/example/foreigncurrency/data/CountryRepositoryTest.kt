package com.example.foreigncurrency.data

import com.example.foreigncurrency.MainCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CountryRepositoryTest {

    private lateinit var fakeDataSource: CountryDataSource
    private lateinit var repository: DefaultCountryRepository

    private val COUNTRIES_SIZE = 2
    private val FAKE_COUNTRIES = listOf(
        Country("Armenian Dram", "AMD"),
        Country("Europe", "EUR")
    )

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setupRepository() {
        fakeDataSource = mock(CountryDataSource::class.java)
        repository = DefaultCountryRepository(fakeDataSource)
    }

    @Test
    fun getCountries_success() = mainCoroutineRule.runBlockingTest {
        //GIVEN
        `when`(fakeDataSource.getCountries()).thenReturn(flow {
            emit(FAKE_COUNTRIES)
        })

        //WHEN
        val result = repository.fetchCountries()

        //THEN
        result.collect { countries ->
            Truth.assertThat(countries).hasSize(COUNTRIES_SIZE)
            Truth.assertThat(countries[1].currencySymbol).matches("EUR")
        }
    }

}
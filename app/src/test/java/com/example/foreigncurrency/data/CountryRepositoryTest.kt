package com.example.foreigncurrency.data

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CountryRepositoryTest {

    private lateinit var fakeDataSource: CountryDataSource
    private lateinit var repository: CountryRepository

    private val COUNTRIES_SIZE = 2
    private val FAKE_COUNTRIES = listOf(
        Country("AMD", "Armenian Dram"),
        Country("Europe", "EUR")
    )

    @Before
    fun setupRepository() {
        fakeDataSource = mock(CountryDataSource::class.java)
        repository = CountryRepository(fakeDataSource)
    }

    @Test
    fun getCountries_success() {
        //GIVEN
        `when`(fakeDataSource.getCountries()).thenReturn(FAKE_COUNTRIES)

        //WHEN
        val result = repository.getCountries()

        //THEN
        Truth.assertThat(result).hasSize(COUNTRIES_SIZE)
        Truth.assertThat(result.get(1).currencySymbol).matches("EUR")
    }

}
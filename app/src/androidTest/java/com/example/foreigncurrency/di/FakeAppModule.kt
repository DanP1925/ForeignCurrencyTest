package com.example.foreigncurrency.di

import com.example.foreigncurrency.FakeRepository
import com.example.foreigncurrency.data.Country
import com.example.foreigncurrency.data.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object FakeAppModule {

    private val FAKE_COUNTRIES = listOf(
        Country("Armenian Dram", "AMD"),
        Country("Europe", "EUR")
    )

    @Singleton
    @Provides
    fun provideCountryRepository(): CountryRepository {
        return FakeRepository()
    }

}
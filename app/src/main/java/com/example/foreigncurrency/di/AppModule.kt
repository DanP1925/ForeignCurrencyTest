package com.example.foreigncurrency.di

import com.example.foreigncurrency.data.CountryDataSource
import com.example.foreigncurrency.data.CountryRepository
import com.example.foreigncurrency.data.remote.CountryRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCountryRemoteDataSource(): CountryDataSource {
        return CountryRemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideCountryRepository(remoteDataSource: CountryDataSource): CountryRepository {
        return CountryRepository(remoteDataSource)
    }
}
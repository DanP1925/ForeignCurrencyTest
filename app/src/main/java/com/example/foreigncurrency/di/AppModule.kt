package com.example.foreigncurrency.di

import com.example.foreigncurrency.data.CountryDataSource
import com.example.foreigncurrency.data.CountryRepository
import com.example.foreigncurrency.data.DefaultCountryRepository
import com.example.foreigncurrency.data.remote.CountryRemoteDataSource
import com.example.foreigncurrency.data.remote.CountryService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("http://data.fixer.io/api/")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()


    @Singleton
    @Provides
    fun provideCountryService(retrofit: Retrofit): CountryService =
        retrofit.create(CountryService::class.java)

    @Singleton
    @Provides
    fun provideCountryRemoteDataSource(countryService: CountryService): CountryRemoteDataSource {
        return CountryRemoteDataSource(countryService)
    }

    @Singleton
    @Provides
    fun provideCountryDataSource(remoteDataSource: CountryRemoteDataSource): CountryDataSource {
        return remoteDataSource
    }

    @Singleton
    @Provides
    fun provideCountryRepository(countryRepository: DefaultCountryRepository): CountryRepository {
        return countryRepository
    }

    @Singleton
    @Provides
    fun provideDefaultCountryRepository(remoteDataSource: CountryDataSource): DefaultCountryRepository {
        return DefaultCountryRepository(remoteDataSource)
    }
}

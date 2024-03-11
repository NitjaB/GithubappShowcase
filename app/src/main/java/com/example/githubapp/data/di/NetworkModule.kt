package com.example.githubapp.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.githubapp.BuildConfig
import com.example.githubapp.data.ApiConstants
import com.example.githubapp.data.api.CredentialsApi
import com.example.githubapp.data.api.SearchApi
import com.example.githubapp.data.interceptors.JsonAcceptHeaderInterceptor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

private const val CONNECTION_TIMEOUT_IN_MINUTES = 1L

private const val DATA = "data"
private const val CREDENTIALS = "credentials"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideChucker(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context)
            .collector(ChuckerCollector(context))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()

    @Provides
    @Singleton
    @Named(DATA)
    fun provideDataOkHttpClient(
        chuckerInterceptor: ChuckerInterceptor
    ) =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(Level.BODY)
                    }
                )
            }
            addInterceptor(chuckerInterceptor)
            connectTimeout(CONNECTION_TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
        }.build()

    @Provides
    @Singleton
    @Named(CREDENTIALS)
    fun provideCredentialsOkHttpClient(
        jsonAcceptHeaderInterceptor: JsonAcceptHeaderInterceptor,
        checkerInterceptor: ChuckerInterceptor,
    ) =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(Level.BODY)
                    }
                )
            }
            addInterceptor(checkerInterceptor)
            addNetworkInterceptor(jsonAcceptHeaderInterceptor)
            connectTimeout(CONNECTION_TIMEOUT_IN_MINUTES, TimeUnit.MINUTES)
        }.build()

    @Provides
    @Singleton
    fun provideParser() = ObjectMapper().apply {
        registerKotlinModule()
    }

    @Provides
    @Singleton
    @Named(DATA)
    fun provideDataApiRetrofit(
        @Named(DATA) httpClient: OkHttpClient,
        parser: ObjectMapper
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_DATA_API_URL)
        .client(httpClient)
        .addConverterFactory(JacksonConverterFactory.create(parser))
        .build()

    @Named(CREDENTIALS)
    fun provideLoginRetrofit(
        @Named(CREDENTIALS) httpClient: OkHttpClient,
        parser: ObjectMapper
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_CREDENCAILS_API_URL)
        .client(httpClient)
        .addConverterFactory(JacksonConverterFactory.create(parser))
        .build()

    @Provides
    @Singleton
    fun provideSearchApi(@Named(DATA) retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

    @Provides
    @Singleton
    fun provideCredentialsApi(@Named(DATA) retrofit: Retrofit): CredentialsApi =
        retrofit.create(CredentialsApi::class.java)
}

package com.example.githubapp.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.githubapp.BuildConfig
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
import javax.inject.Singleton


private const val BASE_GIT_HUB_API_URL = "https://api.github.com/"

private const val CONNECTION_TIMEOUT_IN_MINUTES = 1L

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
    fun provideOkHttpClient(
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
    fun provideParser() = ObjectMapper().apply {
        registerKotlinModule()
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient,
        parser: ObjectMapper
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_GIT_HUB_API_URL)
        .client(httpClient)
        .addConverterFactory(JacksonConverterFactory.create(parser))
        .build()
}

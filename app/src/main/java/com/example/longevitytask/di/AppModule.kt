package com.example.longevitytask.di

import com.example.longevitytask.common.ApiEndPoints
import com.example.longevitytask.data.remote.fetchapi.FetchedAnime
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Singleton
    @Provides
    fun apiService(): FetchedAnime =
        Retrofit.Builder().baseUrl(ApiEndPoints.BASE_URL).client(providesOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(FetchedAnime::class.java)
}
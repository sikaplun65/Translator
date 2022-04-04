package com.sikaplun.gb.kotlin.translator.di.module


import com.sikaplun.gb.kotlin.translator.data.api.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object RetrofitModule {
    private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiInstance(): ApiService = retrofit.create(ApiService::class.java)
}
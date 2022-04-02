package com.sikaplun.gb.kotlin.translator.di.module


import com.sikaplun.gb.kotlin.translator.data.repository.MeaningWordRequest
import org.koin.dsl.module

val dataModule = module {
    single { MeaningWordRequest(apiService = get()) }
}
package com.sikaplun.gb.kotlin.translator.di.module


import com.sikaplun.gb.kotlin.translator.data.repository.MeaningWordRequest
import dagger.Module
import dagger.Provides


@Module
class DataModule {
    @Provides
    fun provideMeaningWord(): MeaningWordRequest =
        MeaningWordRequest(retrofit = RetrofitModule)
}
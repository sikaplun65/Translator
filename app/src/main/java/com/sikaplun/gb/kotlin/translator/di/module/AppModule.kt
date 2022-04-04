package com.sikaplun.gb.kotlin.translator.di.module

import com.sikaplun.gb.kotlin.translator.data.repository.MeaningWordRequest
import com.sikaplun.gb.kotlin.translator.ui.main.MainActivityViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun providesMainActivityViewModelFactory(meaningWord: MeaningWordRequest): MainActivityViewModelFactory =
        MainActivityViewModelFactory(meaningWord)
}
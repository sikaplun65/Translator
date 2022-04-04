package com.sikaplun.gb.kotlin.translator.di.module

import com.sikaplun.gb.kotlin.translator.ui.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainActivityViewModel(meaningWord = get())
    }
}
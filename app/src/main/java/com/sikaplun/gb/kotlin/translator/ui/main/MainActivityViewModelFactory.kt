package com.sikaplun.gb.kotlin.translator.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sikaplun.gb.kotlin.translator.data.repository.MeaningWordRequest
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val meaningWord: MeaningWordRequest): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(meaningWord) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class  $modelClass")
    }
}
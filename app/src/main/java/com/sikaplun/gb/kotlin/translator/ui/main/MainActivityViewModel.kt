package com.sikaplun.gb.kotlin.translator.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sikaplun.gb.kotlin.translator.app.App
import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import com.sikaplun.gb.kotlin.translator.data.repository.MeaningWordRequest
import com.sikaplun.gb.kotlin.translator.room.DataModelLocal
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val meaningWord: MeaningWordRequest) : ViewModel() {

    private val listMeaningWord by lazy { MutableLiveData<List<DataModel>>() }
    private lateinit var disposable: Disposable

    fun findMeaningWord(word: String) {
        meaningWord.findMeaningWord(word)
    }

    fun getMeaningWord(): MutableLiveData<List<DataModel>> {
        disposable = meaningWord.getData().subscribeBy(
            onNext = {
                listMeaningWord.postValue(it)
            },
            onError = { it.printStackTrace() },
            onComplete = { Log.i("Complete", "onCompleted: COMPLETED!") }

        )
        return listMeaningWord
    }

    fun insertWordToDatabaseLocal(applicationContext: App, word: String, meaning: String) {
        viewModelScope.launch(Dispatchers.IO) {
            applicationContext.repository.insert(dataModelLocal = DataModelLocal(word, meaning))
        }
    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }
}
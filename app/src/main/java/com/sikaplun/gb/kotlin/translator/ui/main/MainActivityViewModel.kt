package com.sikaplun.gb.kotlin.translator.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import com.sikaplun.gb.kotlin.translator.data.repository.MeaningWordRequest
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy

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

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }
}
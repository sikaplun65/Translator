package com.sikaplun.gb.kotlin.translator.data.repository

import android.util.Log
import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import com.sikaplun.gb.kotlin.translator.di.module.RetrofitModule
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MeaningWordRequest @Inject constructor(private val retrofit: RetrofitModule) :
    DataSource<List<DataModel>> {

    private val listMeaningWord = BehaviorSubject.create<List<DataModel>>()

    fun findMeaningWord(query: String) {
        retrofit.provideApiInstance()
            .search(query)
            .enqueue(object : Callback<List<DataModel>> {
                override fun onResponse(
                    call: Call<List<DataModel>>,
                    response: Response<List<DataModel>>
                ) {
                    listMeaningWord.onNext(response.body())
                }

                override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }


            })
    }

    override fun getData(): Observable<List<DataModel>> = listMeaningWord
}
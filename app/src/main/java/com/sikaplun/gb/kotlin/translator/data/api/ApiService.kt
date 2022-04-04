package com.sikaplun.gb.kotlin.translator.data.api


import com.sikaplun.gb.kotlin.translator.data.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun search(@Query("search")wordToSearch: String): Call<List<DataModel>>
}
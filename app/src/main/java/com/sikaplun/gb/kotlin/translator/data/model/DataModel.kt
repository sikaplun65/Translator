package com.sikaplun.gb.kotlin.translator.data.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("text")
    var text: String?,

    @SerializedName("meanings")
    val meanings: List<MeaningWord>?
)

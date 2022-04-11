package com.sikaplun.gb.kotlin.translator.data.model

import com.google.gson.annotations.SerializedName

data class MeaningWord(
    @SerializedName("translation")
    val translation: Translation?,

    @SerializedName("imageUrl")
    val imageUrl: String?
)

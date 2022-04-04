package com.sikaplun.gb.kotlin.translator.data.model

import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("text")
    val translation: String?
)

package com.jeluchu.scarlex.models.extractor

import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("Referer")
    val referer: String
)
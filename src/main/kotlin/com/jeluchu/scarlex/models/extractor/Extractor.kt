package com.jeluchu.scarlex.models.extractor

import com.google.gson.annotations.SerializedName

data class Extractor(
    @SerializedName("header")
    val header: Header,

    @SerializedName("status")
    val status: Int,

    @SerializedName("uri")
    val uri: String
)
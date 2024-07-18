package com.jeluchu.scarlex.models.anime

import com.google.gson.annotations.SerializedName

data class AnimeData(
    @SerializedName("airing")
    val airing: Boolean,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("imageUrl")
    val image: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("score")
    val score: Double,

    @SerializedName("temporada")
    val season: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("title_english")
    val englishTitle: String,

    @SerializedName("title_japanese")
    val japaneseTitle: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("year")
    val year: Int
)
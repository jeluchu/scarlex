package com.jeluchu.scarlex.models.anime.lastepisodes

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("anime_id")
    val malId: Int,

    @SerializedName("episode")
    val episode: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("img")
    val img: String,

    @SerializedName("season")
    val season: Int,

    @SerializedName("title")
    val title: String
)
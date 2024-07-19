package com.jeluchu.scarlex.models.anime.servers

import com.google.gson.annotations.SerializedName

data class EpisodeServersItem(
    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("episode")
    val episode: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("mal_id")
    val malId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("season")
    val season: Int,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("url")
    val url: String
)
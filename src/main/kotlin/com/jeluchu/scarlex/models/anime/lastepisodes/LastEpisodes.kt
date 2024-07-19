package com.jeluchu.scarlex.models.anime.lastepisodes

import com.google.gson.annotations.SerializedName

data class LastEpisodes(
    @SerializedName("data")
    val items: List<LastAnimeEpisode>,

    @SerializedName("has_next_page")
    val hasNextPage: Boolean,

    @SerializedName("total_pages")
    val totalPages: Int
)
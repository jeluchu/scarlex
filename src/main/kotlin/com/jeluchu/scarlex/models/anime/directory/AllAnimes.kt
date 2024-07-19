package com.jeluchu.scarlex.models.anime.directory

import com.google.gson.annotations.SerializedName

data class AllAnimes(
    @SerializedName("currentPage")
    val currentPage: Int,

    @SerializedName("data")
    val items: List<AnimeData>,

    @SerializedName("has_next_page")
    val hasNextPage: Boolean,

    @SerializedName("totalPages")
    val totalPages: Int
)
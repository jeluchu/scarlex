package com.jeluchu.scarlex

import com.google.gson.GsonBuilder
import com.jeluchu.scarlex.core.connection.RestClient
import com.jeluchu.scarlex.core.exception.ScarlexException
import com.jeluchu.scarlex.core.utils.InterfaceAdapter
import com.jeluchu.scarlex.core.utils.deserialize
import com.jeluchu.scarlex.core.utils.empty
import com.jeluchu.scarlex.models.anime.AllAnimes
import com.jeluchu.scarlex.models.base.Entity

object Scarlex {
    private var token = String.empty()
    private var restClient = RestClient()
    private val gson = GsonBuilder().registerTypeAdapter(
        Entity::class.java, InterfaceAdapter<Entity>()
    ).create()

    /**
     * Function to set Scarlet API Key
     * @see "https://t.me/scarle_api_bot"
     */
    fun setApiKey(key: String) { token = key }

    /**
     * Function to get all animes.
     * @return Object with data of response and list with all animes
     * @see AllAnimes
     */
    suspend fun getAllAnimes(
        offset: Int? = null,
        page: Int? = null,
        genres: String? = null
    ): AllAnimes {
        val endpoint = "anime/all"
        val params = mutableListOf<String>()

        offset?.let { params.add("perOffset=$it") }
        page?.let { params.add("type=$it") }
        genres?.let { params.add("rating=$it") }

        if (token.isNotEmpty()) params.add("token=$token")
        else throw ScarlexException("You need an API Key to get the answer")

        val fullEndpoint = if (params.isNotEmpty()) "$endpoint?${params.joinToString("&")}"
        else endpoint

        return gson.deserialize(
            restClient.request(fullEndpoint),
            AllAnimes::class.java
        )
    }
}
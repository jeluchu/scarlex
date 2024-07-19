package com.jeluchu.scarlex

import com.google.gson.GsonBuilder
import com.jeluchu.scarlex.core.connection.RestClient
import com.jeluchu.scarlex.core.exception.ScarlexException
import com.jeluchu.scarlex.core.utils.InterfaceAdapter
import com.jeluchu.scarlex.core.utils.deserialize
import com.jeluchu.scarlex.core.utils.empty
import com.jeluchu.scarlex.models.anime.directory.AllAnimes
import com.jeluchu.scarlex.models.anime.lastepisodes.LastEpisodes
import com.jeluchu.scarlex.models.anime.servers.EpisodeServersItem
import com.jeluchu.scarlex.models.base.Entity
import com.jeluchu.scarlex.models.extractor.Extractor
import com.jeluchu.scarlex.models.extractor.Servers

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
     * Function to get final link for video player
     * @return Object with data of video
     * @see Extractor
     */
    suspend fun getServer(
        url: String,
        serverId: Servers,
    ): Extractor {
        if (token.isEmpty()) throw ScarlexException("You need an API Key to get the answer")
        return gson.deserialize(
            restClient.request("extractor/get-video?token=$token&url=$url&server_id=${serverId.id}"),
            Extractor::class.java
        )
    }

    /**
     * Function to get all animes.
     * @return Object with data of all animes in a list
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

    /**
     * Function to get all last episodes from sources
     * @return Object with data of last episodes
     * @see LastEpisodes
     */
    suspend fun getLastEpisodes(
        offset: Int? = null,
        page: Int? = null
    ): LastEpisodes {
        val endpoint = "anime/last-episodes"
        val params = mutableListOf<String>()

        offset?.let { params.add("perOffset=$it") }
        page?.let { params.add("type=$it") }

        if (token.isNotEmpty()) params.add("token=$token")
        else throw ScarlexException("You need an API Key to get the answer")

        val fullEndpoint = if (params.isNotEmpty()) "$endpoint?${params.joinToString("&")}"
        else endpoint

        return gson.deserialize(
            restClient.request(fullEndpoint),
            LastEpisodes::class.java
        )
    }

    /**
     * Function to get all last episodes from sources
     * @return Object with data of last episodes
     * @see LastEpisodes
     */
    suspend fun getEpisodeServers(
        id: Int,
        malId: Int
    ): List<EpisodeServersItem> {
        if (token.isEmpty()) throw ScarlexException("You need an API Key to get the answer")
        return gson.deserialize(
            restClient.request("anime/episodes/servers?token=$token&id=$id&mal_id=$malId")
        )
    }
}
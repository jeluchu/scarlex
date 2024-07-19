package com.jeluchu.scarlex

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class AnimeTests {
    @Test
    fun `on getAllAnimes and return anime list`() {
        runBlocking {
            val result = runBlocking {  Scarlex.getAllAnimes() }
            assertTrue(result.items.isNotEmpty())
        }

        runBlocking { delay(3000) }
    }

    @Test
    fun `on getLastEpisodes and return anime list`() {
        runBlocking {
            val result = runBlocking {  Scarlex.getLastEpisodes() }
            assertTrue(result.items.isNotEmpty())
        }

        runBlocking { delay(3000) }
    }

    @Test
    fun `on getEpisodeServers and return episode server list`() {
        runBlocking {
            Scarlex.setApiKey("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjk1MjA4NjQ5MywidXNlck5hbWUiOiJKZWx1IiwiaWF0IjoxNzIxMzc0MjcxfQ.4qd3pDqdzAfH8NjBoV_aCFTyGGwXGxCFa8gzEd8LK6c")

            val result = runBlocking {  Scarlex.getEpisodeServers(id = 2116, malId = 1) }
            assertTrue(result.isNotEmpty())
        }

        runBlocking { delay(3000) }
    }

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun setup() {
            Scarlex
        }
    }
}
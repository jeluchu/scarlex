package com.jeluchu.scarlex

import com.jeluchu.scarlex.models.extractor.Servers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ExtractorTests {
    @Test
    fun `on getAllAnimes and return anime list`() {
        val link = "https://filemoon.sx/e/18dty25ai7g8"

        runBlocking {
            val result = runBlocking {
                Scarlex.getServer(
                    url = link,
                    serverId = Servers.FILEMOON
                )
            }
            assertTrue(result.uri.isNotEmpty())
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
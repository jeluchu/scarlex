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

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun setup() {
            Scarlex
        }
    }
}
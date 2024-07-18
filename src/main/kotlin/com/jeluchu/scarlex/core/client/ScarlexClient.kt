package com.jeluchu.scarlex.core.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson
import okhttp3.Protocol

open class ScarlexClient {
    protected val httpClient by lazy {
        HttpClient(OkHttp) {
            engine {
                config {
                    protocols(listOf(Protocol.HTTP_1_1))
                    retryOnConnectionFailure(true)
                }
            }

            install(ContentNegotiation) {
                gson()
            }

            expectSuccess = false
        }
    }
}
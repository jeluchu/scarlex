package com.jeluchu.scarlex.core.connection

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.jeluchu.scarlex.core.client.ScarlexClient
import com.jeluchu.scarlex.core.exception.ScarlexException
import com.jeluchu.scarlex.core.utils.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.headers

/**
 * Class that handle request.
 * @param isDebug: a boolean that indicate if you run it on debug or not. If yes, it'll throw exception if something happen.
 */
class RestClient(private val isDebug: Boolean = false) : ScarlexClient() {
    private val gson = Gson()
    private val client = httpClient

    suspend fun request(endPoint: String, data: JsonObject? = null): JsonElement {
        runCatching {
            var url = BASE_URL + endPoint
            if (data != null) {
                url += "?" + data.entrySet().joinToString("&") { entry ->
                    "${entry.key}=${entry.value}"
                }
            }

            val response = client.get(url) {
                headers {
                    append(HttpHeaders.Accept, "application/json")
                }
            }

            val body = response.body<JsonElement>()
            val contentType = response.headers["Content-Type"]
            val json = if (contentType?.contains("application/json", true) == true) {
                gson.fromJson(body, JsonElement::class.java)
            } else null

            if (response.status.value !in 200..299) {
                if (response.status.value in 500..599) {
                    val ex = Exception("An internal server error has occurred, code: ${response.status.value}")
                    if (isDebug) throw ex else exceptionHandler(ex)
                } else {
                    val ex = ScarlexException(
                        "Scarlet API returns code ${response.status.value} and body ${json?.toString()}",
                        response.status.value
                    )

                    if (isDebug) throw ex
                    else exceptionHandler(ex)
                }
            }

            return json ?: JsonObject()
        }.getOrElse { throwable -> throw throwable }
    }

    private fun exceptionHandler(ex: Exception, message: String? = null) {
        if (message.isNullOrEmpty()) Log.error("Something went wrong! Exception: ${ex.localizedMessage}")
        else Log.error(ex, "Something went wrong! Exception: ${ex.localizedMessage}")
    }

    companion object {
        const val BASE_URL = "https://scarlet-api.online/api/"
    }
}
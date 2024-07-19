package com.jeluchu.scarlex.core.utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Gson extension function to deserialize [JsonElement] to [T]
 * @param jsonElement: Json element that want to deserialize.
 * @param type: return type
 * @return T as the deserialize result.
 */
fun <T : Any> Gson.deserialize(jsonElement: JsonElement, type: Type): T {
    return this.fromJson(jsonElement, type)
}

fun <T : Any> Gson.deserialize(jsonElement: JsonElement): List<T> {
    val type = object : TypeToken<List<T>>() {}.type
    return this.fromJson(jsonElement, type)
}
package com.example

import androidx.datastore.core.Serializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Serializable
data class AppPreferences(
    val date: String? = null
)

object AppPreferencesSerializer : Serializer<AppPreferences> {

    override val defaultValue: AppPreferences
        get() = AppPreferences()

    override suspend fun readFrom(input: InputStream): AppPreferences {
        return try {
            Json.decodeFromString(
                deserializer = AppPreferences.serializer(),
                string = input.readBytes().decodeToString()
            )

        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: AppPreferences, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppPreferences.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}

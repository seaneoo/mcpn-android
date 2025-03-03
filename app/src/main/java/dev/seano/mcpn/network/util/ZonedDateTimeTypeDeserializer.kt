package dev.seano.mcpn.network.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.time.ZonedDateTime

class ZonedDateTimeTypeDeserializer : JsonDeserializer<ZonedDateTime> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): ZonedDateTime {
        return ZonedDateTime.parse(json?.asString ?: "")
            ?: throw JsonParseException("Invalid date format")
    }
}

package dev.seano.mcpn.network.api.model

import dev.seano.mcpn.network.util.ZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class PatchNotes(
    val version: Int,
    val entries: List<Entry>,
) {

    @Serializable
    data class Entry(
        val title: String,
        val version: String,
        val type: VersionManifest.Version.Type,
        val image: Image,
        val contentPath: String,
        val id: String,
        @Serializable(with = ZonedDateTimeSerializer::class) val date: ZonedDateTime,
        val shortText: String,
    ) {

        @Serializable
        data class Image(val title: String, val url: String) {

            fun getFullUrl() = "https://launchercontent.mojang.com${url}"
        }
    }
}

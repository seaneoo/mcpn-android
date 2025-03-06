package dev.seano.mcpn.network.api.model

import dev.seano.mcpn.network.util.ZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

interface PatchNoteEntry

@Serializable
data class PatchNotes<T : PatchNoteEntry>(
    val version: Int,
    val entries: List<T>,
) {

    @Serializable
    data class Image(val title: String, val url: String) {

        fun getFullUrl() = "https://launchercontent.mojang.com${url}"
    }

    @Serializable
    data class JavaEntry(
        val title: String,
        val version: String,
        val type: VersionManifest.Version.Type,
        val image: Image,
        val contentPath: String,
        val id: String,
        @Serializable(with = ZonedDateTimeSerializer::class) val date: ZonedDateTime,
        val shortText: String,
    ) : PatchNoteEntry

    @Serializable
    data class BedrockEntry(
        val title: String,
        val version: String,
        val patchNoteType: String? = null,
        val image: Image,
        val contentPath: String,
        val id: String,
        @Serializable(with = ZonedDateTimeSerializer::class) val date: ZonedDateTime,
        val shortText: String,
    ) : PatchNoteEntry
}

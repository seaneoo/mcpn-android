package dev.seano.mcpn.network.api.model

import dev.seano.mcpn.network.util.ZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

interface PatchNoteEntry

/**
 * Patch notes for either Java or Bedrock edition.
 * @param version N/A
 * @param entries List of [PatchNotes.JavaEntry] or [PatchNotes.BedrockEntry].
 */
@Serializable
data class PatchNotes<T : PatchNoteEntry>(
    val version: Int,
    val entries: List<T>,
) {

    /**
     * Represents a single image in a patch note.
     * @param title The image file name.
     * @param url The url to the image (relative to the API base url). See [getFullUrl].
     */
    @Serializable
    data class Image(val title: String, val url: String) {

        /**
         * The full url to the image. See [url].
         */
        fun getFullUrl() = "https://launchercontent.mojang.com${url}"
    }

    /**
     * Represents a Java edition patch note.
     * Identical to [BedrockEntry] with the exception of the [type] param.
     * @param title
     * @param version
     * @param type
     * @param image
     * @param contentPath
     * @param id
     * @param date
     * @param shortText
     */
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

    /**
     * Represents a Bedrock edition patch note.
     * Identical to [JavaEntry] with the exception of the [patchNoteType] param.
     * @param title
     * @param version
     * @param patchNoteType
     * @param image
     * @param contentPath
     * @param id
     * @param date
     * @param shortText
     */
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

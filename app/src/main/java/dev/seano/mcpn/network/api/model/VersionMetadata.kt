package dev.seano.mcpn.network.api.model

import dev.seano.mcpn.network.util.ZonedDateTimeSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

/**
 * The metadata for a single game version.
 * @param downloads The metadata for the client and server downloads.
 * @param id The version number (i.e., "1.21.4" or "25w09b").
 * @param releaseTime The date and time the version was initially released.
 * @param time The date and time the metadata was last updated.
 * @param type The version type (i.e., "release", "snapshot", etc).
 */
@Serializable
data class VersionMetadata(
    val downloads: Downloads,
    val id: String,
    @Serializable(with = ZonedDateTimeSerializer::class) val releaseTime: ZonedDateTime,
    @Serializable(with = ZonedDateTimeSerializer::class) val time: ZonedDateTime,
    val type: VersionManifest.Version.Type,
) {

    /**
     * Represents the downloadable "client" and "server" Jar files.
     * @param client The metadata for the client download.
     * @param server The metadata for the server download.
     */
    @Serializable
    data class Downloads(
        val client: Download,
        val server: Download,
    ) {

        /**
         * Represents a single downloadable Jar file.
         * @param sha1 The SHA-1 hash of the download.
         * @param size The size of the download in bytes.
         * @param url The URL to download the Jar.
         */
        @Serializable
        data class Download(
            val sha1: String,
            val size: Int,
            val url: String,
        )
    }
}

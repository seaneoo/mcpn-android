package dev.seano.mcpn.network.api.model

import java.time.ZonedDateTime

/**
 * The metadata for a single game version.
 * @param downloads The metadata for the client and server downloads.
 * @param id The version number (i.e., "1.21.4" or "25w09b").
 * @param releaseTime The date and time the version was initially released.
 * @param time The date and time the metadata was last updated.
 * @param type The version type (i.e., "release", "snapshot", etc).
 */
data class VersionMetadata(
    val downloads: Downloads,
    val id: String,
    val releaseTime: ZonedDateTime,
    val time: ZonedDateTime,
    val type: VersionManifest.Version.Type,
) {

    /**
     * Represents the downloadable "client" and "server" Jar files.
     * @param client The metadata for the client download.
     * @param server The metadata for the server download.
     */
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
        data class Download(
            val sha1: String,
            val size: Int,
            val url: String,
        )
    }
}

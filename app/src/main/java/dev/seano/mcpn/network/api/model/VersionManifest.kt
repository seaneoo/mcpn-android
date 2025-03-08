package dev.seano.mcpn.network.api.model

import dev.seano.mcpn.network.api.model.VersionManifest.Version.Type.Alpha
import dev.seano.mcpn.network.api.model.VersionManifest.Version.Type.Beta
import dev.seano.mcpn.network.api.model.VersionManifest.Version.Type.Release
import dev.seano.mcpn.network.api.model.VersionManifest.Version.Type.Snapshot
import dev.seano.mcpn.network.util.ZonedDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

/**
 * A manifest containing all game versions released to date.
 * @param latest The latest release and snapshot versions.
 * @param versions A list of all game versions.
 */
@Serializable
data class VersionManifest(
    val latest: Latest,
    val versions: List<Version>,
) {

    /**
     * The latest release and snapshot versions.
     * @param release The latest release version.
     * @param snapshot The latest snapshot version.
     */
    @Serializable
    data class Latest(
        val release: String,
        val snapshot: String,
    )

    /**
     * Represents a single game version.
     * @param id The version number (i.e., "1.21.4" or "25w09b").
     * @param type The version type (i.e., "release", "snapshot", etc).
     * @param url The url for the version metadata.
     * @param time The date and time the metadata was last updated.
     * @param releaseTime The date and time the version was initially released.
     * @param sha1 The SHA-1 hash of the version metadata.
     */
    @Serializable
    data class Version(
        val id: String,
        val type: Type,
        val url: String,
        @Serializable(with = ZonedDateTimeSerializer::class) val time: ZonedDateTime,
        @Serializable(with = ZonedDateTimeSerializer::class) val releaseTime: ZonedDateTime,
        val sha1: String,
    ) {

        /**
         * @property Release The version is a full release (>= 1.0).
         * @property Snapshot The version is a snapshot (i.e., "25w09b").
         * @property Beta The version is a beta release (>= b1.0, <= b1.8.1).
         * @property Alpha The version is an alpha release (>= rd-132211, <= a1.2.6).
         */
        @Serializable
        enum class Type {

            @SerialName("release")
            Release,

            @SerialName("snapshot")
            Snapshot,

            @SerialName("old_beta")
            Beta,

            @SerialName("old_alpha")
            Alpha
        }
    }
}

package dev.seano.mcpn.network.api

import dev.seano.mcpn.network.NetworkClient
import dev.seano.mcpn.network.NetworkError
import dev.seano.mcpn.network.Result
import dev.seano.mcpn.network.api.model.VersionManifest
import dev.seano.mcpn.network.api.model.VersionMetadata
import dev.seano.mcpn.network.onFailure
import dev.seano.mcpn.network.onSuccess
import io.ktor.http.appendPathSegments
import timber.log.Timber

class ApiService(private val networkClient: NetworkClient) {

    object Endpoints {

        const val PISTON_META_BASE_URL = "https://piston-meta.mojang.com"
        const val VERSION_MANIFEST = "mc/game/version_manifest_v2.json"
        const val VERSION_METADATA = "v1/packages/%s/%s"
    }

    private suspend inline fun <reified T> fetch(
        urlString: String,
        vararg pathSegments: String,
    ): Result<T, NetworkError> {
        return networkClient.call<T>(urlString = urlString) {
            url {
                appendPathSegments(*pathSegments)
            }
        }
            .onSuccess { Timber.d("Successfully fetched ${T::class.simpleName}") }
            .onFailure { Timber.d("Failed to fetch for ${T::class.simpleName}: ${it.message}") }
    }

    suspend fun getVersionManifest(): Result<VersionManifest, NetworkError> =
        fetch<VersionManifest>(Endpoints.PISTON_META_BASE_URL, Endpoints.VERSION_MANIFEST)

    suspend fun getVersionMetadata(
        id: String,
        sha1: String,
    ): Result<VersionMetadata, NetworkError> = fetch<VersionMetadata>(
        Endpoints.PISTON_META_BASE_URL, Endpoints.VERSION_METADATA.format(sha1, "$id.json")
    )
}

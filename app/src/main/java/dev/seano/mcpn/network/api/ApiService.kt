package dev.seano.mcpn.network.api

import dev.seano.mcpn.network.NetworkClient
import dev.seano.mcpn.network.NetworkError
import dev.seano.mcpn.network.Result
import dev.seano.mcpn.network.api.model.VersionManifest
import dev.seano.mcpn.network.api.model.VersionMetadata
import io.ktor.http.appendPathSegments

class ApiService(private val networkClient: NetworkClient) {

    suspend fun getVersionManifest(): Result<VersionManifest, NetworkError> =
        networkClient.call<VersionManifest>(urlString = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json")

    suspend fun getVersionMetadata(id: String, sha1: String) =
        networkClient.call<VersionMetadata>(urlString = "https://piston-meta.mojang.com/v1/packages") {
            url { appendPathSegments(sha1, "$id.json") }
        }
}

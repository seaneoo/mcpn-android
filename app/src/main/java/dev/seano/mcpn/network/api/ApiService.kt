package dev.seano.mcpn.network.api

import dev.seano.mcpn.network.NetworkClient
import dev.seano.mcpn.network.NetworkError
import dev.seano.mcpn.network.Result
import dev.seano.mcpn.network.api.model.VersionManifest

class ApiService(private val networkClient: NetworkClient) {

    suspend fun getVersionManifest(): Result<VersionManifest, NetworkError> =
        networkClient.call<VersionManifest>(urlString = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json")
}

package dev.seano.mcpn.di

import dev.seano.mcpn.network.NetworkClient
import dev.seano.mcpn.network.api.ApiService

class AppModuleImpl : AppModule {

    override val networkClient: NetworkClient by lazy { NetworkClient() }
    override val apiService: ApiService by lazy { ApiService(networkClient) }
}

package dev.seano.mcpn.di

import dev.seano.mcpn.network.NetworkClient
import dev.seano.mcpn.network.api.ApiService

interface AppModule {

    val networkClient: NetworkClient
    val apiService: ApiService
}

package dev.seano.mcpn.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.UnknownHostException

class NetworkClient {

    @PublishedApi
    internal val httpClient: HttpClient by lazy {
        HttpClient(CIO) {
            install(Logging)
            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 5000
                socketTimeoutMillis = 10000
            }
            install(ContentNegotiation) {
                gson { }
            }
        }
    }

    suspend inline fun <reified T> call(
        httpMethod: HttpMethod = HttpMethod.Get,
        urlString: String,
        crossinline block: HttpRequestBuilder.() -> Unit = {},
    ): Result<T, NetworkError> = withContext(Dispatchers.IO) {
        try {
            val response = httpClient.request(urlString) {
                method = httpMethod
                block()
            }
            when (response.status.value) {
                in 200..299 -> {
                    val data = response.body<T>()
                    Result.Success(data)
                }

                401 -> Result.Failure(NetworkError.Unauthorized)
                404 -> Result.Failure(NetworkError.NotFound)
                in 500..599 -> Result.Failure(NetworkError.ServerError)
                else -> Result.Failure(NetworkError.Unknown)
            }
        } catch (e: UnknownHostException) {
            Result.Failure(NetworkError.NoInternet)
        } catch (e: NoRouteToHostException) {
            Result.Failure(NetworkError.NoInternet)
        } catch (e: ConnectException) {
            Result.Failure(NetworkError.ConnectionRefused)
        } catch (e: SocketTimeoutException) {
            Result.Failure(NetworkError.TimedOut)
        } catch (e: SerializationException) {
            Result.Failure(NetworkError.Serialization)
        } catch (e: Exception) {
            Result.Failure(NetworkError.Unknown)
        }
    }
}

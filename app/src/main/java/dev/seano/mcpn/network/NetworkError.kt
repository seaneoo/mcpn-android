package dev.seano.mcpn.network

enum class NetworkError(override val message: String) : Error {

    ConnectionRefused("Connection refused"),
    NoInternet("No internet connection"),
    NotFound("Resource not found"),
    Serialization("Serialization error"),
    ServerError("Server error"),
    TimedOut("Timed out"),
    Unauthorized("Unauthorized"),
    Unknown("Unknown error");

    override fun toString(): String {
        return "NetworkError(message='$message')"
    }
}

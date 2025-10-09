package backend.studybotbackend.domain.response.auth

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
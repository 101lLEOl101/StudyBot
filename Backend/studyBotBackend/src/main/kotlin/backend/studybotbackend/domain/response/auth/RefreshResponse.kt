package backend.studybotbackend.domain.response.auth

data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String
)

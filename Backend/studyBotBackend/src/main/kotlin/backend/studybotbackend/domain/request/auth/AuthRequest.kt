package backend.studybotbackend.domain.request.auth

data class AuthRequest(
    val nickName: String,
    val password: String,
)

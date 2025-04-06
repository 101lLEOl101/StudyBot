package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.request.auth.AuthRequest
import backend.studybotbackend.domain.response.auth.AuthResponse

interface AuthService {
    fun login(request: AuthRequest): State<AuthResponse>
}
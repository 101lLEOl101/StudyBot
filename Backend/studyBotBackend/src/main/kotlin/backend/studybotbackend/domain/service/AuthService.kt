package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.request.auth.AuthRequest
import backend.studybotbackend.domain.request.auth.RefreshRequest
import backend.studybotbackend.domain.response.auth.AuthResponse
import backend.studybotbackend.domain.response.auth.RefreshResponse

interface AuthService {
    fun login(request: AuthRequest): State<AuthResponse>

    fun generateBotToken(): State<String>
    fun refresh(request: RefreshRequest): State<RefreshResponse>
}
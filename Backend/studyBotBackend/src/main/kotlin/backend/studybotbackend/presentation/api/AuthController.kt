package backend.studybotbackend.presentation.api;

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.request.auth.AuthRequest
import backend.studybotbackend.domain.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(Routes.AUTH_PATH)
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("login")
    fun login(
        @RequestBody request: AuthRequest
    ): ResponseEntity<Any> = authService.login(request).asResponse()

}

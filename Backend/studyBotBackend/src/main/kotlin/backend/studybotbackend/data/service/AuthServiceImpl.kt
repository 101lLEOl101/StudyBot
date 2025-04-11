package backend.studybotbackend.data.service

import backend.studybotbackend.core.jwt.JwtService
import backend.studybotbackend.core.jwt.WorkerDetailsServise
import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.WorkerDao
import backend.studybotbackend.data.util.WorkerDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidCredentials
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.request.auth.AuthRequest
import backend.studybotbackend.domain.request.auth.RefreshRequest
import backend.studybotbackend.domain.response.auth.AuthResponse
import backend.studybotbackend.domain.response.auth.RefreshResponse
import backend.studybotbackend.domain.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class AuthServiceImpl(
    private var workerDao: WorkerDao,
    private var jwtService: JwtService,
    private var passwordEncoder: PasswordEncoder,
    private val userDetailsService: WorkerDetailsServise
): AuthService, WorkerDomainConverter(){


    override fun login(request: AuthRequest): State<AuthResponse> {
        val worker = workerDao.findByNickName(request.nickName).getOrElse { throw InvalidCredentials("Invalid username or password") }.asDomain()


        if(!passwordEncoder.matches(request.password,worker.password)){
            throw InvalidCredentials("Invalid username or password")
        }

        return State.Success(AuthResponse(jwtService.generateAccessToken(worker),jwtService.generateRefreshToken(worker)))
    }

    override fun generateBotToken(): State<String> {
        return State.Success(jwtService.generateBotToken())
    }

    override fun refresh(request: RefreshRequest): State<RefreshResponse> {
        val token = request.refreshToken
        if (jwtService.isTokenValid(token) && jwtService.parseTokenType(token) == "refresh"){
            val worker = userDetailsService.loadUserByUsername(jwtService.parseNickName(token)).worker

            return  State.Success(RefreshResponse(jwtService.generateAccessToken(worker),jwtService.generateRefreshToken(worker)))
        }
        throw InvalidCredentials()
    }
}
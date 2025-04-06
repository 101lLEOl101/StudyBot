package backend.studybotbackend.data.service

import backend.studybotbackend.core.jwt.JwtService
import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.WorkerDao
import backend.studybotbackend.data.util.WorkerDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidCredentials
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.request.auth.AuthRequest
import backend.studybotbackend.domain.response.auth.AuthResponse
import backend.studybotbackend.domain.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class AuthServiceImpl(
    private var workerDao: WorkerDao,
    private var jwtService: JwtService,
    private var passwordEncoder: PasswordEncoder
): AuthService, WorkerDomainConverter(){


    override fun login(request: AuthRequest): State<AuthResponse> {
        val worker = workerDao.findByNickName(request.nickName).getOrElse { throw InvalidCredentials("Invalid username or password") }.asDomain()


        if(!passwordEncoder.matches(request.password,worker.password)){
            throw InvalidCredentials("Invalid username or password")
        }

        return State.Success(AuthResponse(jwtService.generateToken(worker,true),jwtService.generateToken(worker,false)))
    }
}
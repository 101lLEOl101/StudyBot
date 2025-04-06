package backend.studybotbackend.core.jwt

import backend.studybotbackend.core.config.JwtProperties
import backend.studybotbackend.domain.model.worker.Role
import backend.studybotbackend.domain.model.worker.Worker
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    private val jwtProperties: JwtProperties
) {
    @Value("\${SECRET_JWT}")
    private lateinit var jwtSecret: String

    @Value("\${ISSUER_JWT}")
    private lateinit var jwtIssuer: String


    fun generateToken(worker: Worker, isAcces: Boolean): String {
        val now = Date()
        val expiry =
            Date(System.currentTimeMillis() + if (isAcces) jwtProperties.accessTokenExpirationTime else jwtProperties.refreshTokenExpirationTime)

        return Jwts.builder()
            .setSubject(worker.nickName)
            .setIssuer(jwtIssuer)
            .setIssuedAt(now)
            .setExpiration(expiry).setHeaderParams(
                mapOf(
                    "alg" to "RSA256",
                    "type" to "JWT",
                    "is-acces" to isAcces
                )
            )
            .addClaims(
                mapOf(
                    "role" to worker.workerRole,
                    "full-name" to "${worker.firstName} ${worker.lastName}"
                )
            )
            .signWith(SignatureAlgorithm.HS256, jwtSecret.toByteArray())
            .compact()
    }

    fun parseNickName(token: String): String =
        Jwts.parser().setSigningKey(jwtSecret.toByteArray()).parseClaimsJws(token).body.subject

    fun parseRole(token: String): Role =
        Jwts.parser().setSigningKey(jwtSecret.toByteArray()).parseClaimsJws(token).body["role"] as Role


}

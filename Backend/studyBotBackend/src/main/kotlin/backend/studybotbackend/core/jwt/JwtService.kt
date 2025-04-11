package backend.studybotbackend.core.jwt

import backend.studybotbackend.core.config.JwtProperties
import backend.studybotbackend.domain.model.worker.Role
import backend.studybotbackend.domain.model.worker.Worker
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService(
    private val jwtProperties: JwtProperties,
    private val jwtParser: JwtParser
) {
    @Value("\${SECRET_JWT}")
    private lateinit var jwtSecret: String

    @Value("\${ISSUER_JWT}")
    private lateinit var jwtIssuer: String

    fun generateAccessToken(worker: Worker): String {
        val now = Date()
        val expiry =
            Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationTime)


        return Jwts.builder()
            .setSubject(worker.nickName)
            .setIssuer(jwtIssuer)
            .setIssuedAt(now)
            .setExpiration(expiry).setHeaderParams(
                mapOf(
                    "alg" to "RSA256",
                    "token-type" to "access"
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

    fun generateRefreshToken(worker: Worker): String {
        val now = Date()
        val expiry =
            Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpirationTime)


        return Jwts.builder()
            .setSubject(worker.nickName)
            .setIssuer(jwtIssuer)
            .setIssuedAt(now)
            .setExpiration(expiry).setHeaderParams(
                mapOf(
                    "alg" to "RSA256",
                    "token-type" to "refresh"
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

    fun generateBotToken(): String {
        val now = Date()
        val expiry =
            Date(System.currentTimeMillis() + jwtProperties.botTokenExpirationTime)


        return Jwts.builder()
            .setSubject("server")
            .setIssuer(jwtIssuer)
            .setIssuedAt(now)
            .setExpiration(expiry).setHeaderParams(
                mapOf(
                    "alg" to "RSA256",
                    "token-type" to "bot"
                )
            )
            .addClaims(
                mapOf(
                )
            )
            .signWith(SignatureAlgorithm.HS256, jwtSecret.toByteArray())
            .compact()
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            val claimsJws = jwtParser.parseClaimsJws(token)
            val expiration = claimsJws.body.expiration
            expiration.after(Date())
        } catch (e: JwtException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun parseNickName(token: String): String =
        jwtParser.parseClaimsJws(token).body.subject

    fun parseRole(token: String): Role =
        jwtParser.parseClaimsJws(token).body["role"] as Role

    fun parseTokenType(token: String): String =
        jwtParser.parse(token).header["token-type"] as String


}

package backend.studybotbackend.core.config

import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
    var accessTokenExpirationTime: Long = 0
    var refreshTokenExpirationTime: Long = 0
    var botTokenExpirationTime: Long = 0
    @Value("\${SECRET_JWT}")
    private lateinit var jwtSecret: String

    @Bean
    fun jwtParser(): JwtParser {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.toByteArray()))
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()


}

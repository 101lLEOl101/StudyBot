package backend.studybotbackend.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
    var accessTokenExpirationTime: Long = 0
    var refreshTokenExpirationTime: Long = 0
}

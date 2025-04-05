package backend.studybotbackend.core.config

import backend.studybotbackend.core.jwt.BotTokenAuthFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Configuration
@EnableWebSecurity
class WebSecurityConfig (
    //private val jwtFilter: JwtAuthenticationFilter
){
    @Value("\${BOT_API_TOKEN}")
    private lateinit var botApiToken: String

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("${Routes.STATUS_API}/test-bot-req").hasRole("BOT")
                    .anyRequest().permitAll()
            }
            .addFilterBefore(
                BotTokenAuthFilter(botApiToken),
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}

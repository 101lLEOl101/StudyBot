package backend.studybotbackend.core.config

import backend.studybotbackend.core.jwt.BotTokenAuthFilter
import backend.studybotbackend.core.jwt.WorkerTokenAuthFilter
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
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
    private val botTokenAuthFilter: BotTokenAuthFilter,
    private val workerTokenAuthFilter: WorkerTokenAuthFilter
){

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("${Routes.STATUS_API}/test-bot-req").hasRole("BOT")
                    .requestMatchers("${Routes.WORKER_API}/self-info").hasRole("TEACHER")
                    .anyRequest().permitAll()
            }
            .addFilterBefore(
                botTokenAuthFilter,
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter::class.java
            )
            .addFilterBefore(
                workerTokenAuthFilter,
                org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter::class.java
            )
            .build()
    }



}

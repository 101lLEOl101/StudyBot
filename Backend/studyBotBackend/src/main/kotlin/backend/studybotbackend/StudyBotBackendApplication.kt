package backend.studybotbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import java.time.LocalDateTime
import java.time.temporal.TemporalField

@SpringBootApplication()
class StudyBotBackendApplication

fun main(args: Array<String>) {
    runApplication<StudyBotBackendApplication>(*args)

}

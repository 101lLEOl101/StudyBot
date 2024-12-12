package backend.studybotbackend.domain.model.test

import backend.studybotbackend.domain.model.Domain
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class Test(
    val id: Long,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createTime: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val expiresTime: LocalDateTime,
    val discipline: Long,
    val testName: String,
    val questions: List<Long>,
    val results: List<Long>,
) : Domain {
    val isExpired = LocalDateTime.now().isAfter(expiresTime)
    companion object {
        fun new(
            createTime: LocalDateTime,
            expiresTime: LocalDateTime,
            discipline: Long,
            testName: String,
            questions: List<Long> = listOf(),
            results: List<Long> = listOf(),
        ) = Test(
            0,
            createTime,
            expiresTime,
            discipline,
            testName,
            questions,
            results,
        )
    }
}
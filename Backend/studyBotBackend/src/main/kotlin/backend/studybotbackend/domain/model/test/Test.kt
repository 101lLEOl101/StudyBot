package backend.studybotbackend.domain.model.test

import backend.studybotbackend.domain.model.Domain
import java.time.LocalDateTime

data class Test(
    val id: Long,
    val createTime: LocalDateTime,
    val expiresTime: LocalDateTime,
    val discipline: Long,
    val testName: String,
    val questions: List<Long>,
    val results: List<Long>,
) : Domain {
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
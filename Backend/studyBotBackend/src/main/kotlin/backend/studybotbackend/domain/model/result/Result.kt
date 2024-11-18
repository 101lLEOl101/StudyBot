package backend.studybotbackend.domain.model.result

import backend.studybotbackend.domain.model.Domain
import java.time.LocalDateTime

data class Result(
    val id: Long,
    val startTime: LocalDateTime,
    val finishTime: LocalDateTime?,
    val student: Long,
    val test: Long,
    val answers: List<Long>,
) : Domain {
    companion object {
        fun new(
            createTime: LocalDateTime,
            expiresTime: LocalDateTime,
            student: Long,
            test: Long,
            answers: List<Long> = listOf(),
        ) = Result(
            0,
            createTime,
            expiresTime,
            student,
            test,
            answers,
        )
    }
}
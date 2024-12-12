package backend.studybotbackend.domain.model.result

import backend.studybotbackend.domain.model.Domain
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class Result(
    val id: Long,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val startTime: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val finishTime: LocalDateTime?,
    val student: Long,
    val test: Long,
    val answers: List<Long>,
) : Domain {
    val isFinished: Boolean = finishTime != null

    companion object {
        fun new(
            createTime: LocalDateTime,
            finishTime: LocalDateTime?,
            student: Long,
            test: Long,
            answers: List<Long> = listOf(),
        ) = Result(
            0,
            createTime,
            finishTime,
            student,
            test,
            answers,
        )
    }
}
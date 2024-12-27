package backend.studybotbackend.domain.model.result

import backend.studybotbackend.domain.model.test.AnswerFull
import java.time.LocalDateTime

class ResultFull(
    val id: Long,
    val startTime: LocalDateTime,
    val finishTime: LocalDateTime?,
    val student: Long,
    val test: Long,
    val answers: List<AnswerFull>,
)
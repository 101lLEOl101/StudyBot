package backend.studybotbackend.domain.model.answerOption

import backend.studybotbackend.domain.model.Domain

data class AnswerOption(
    val id: Long,
    val correct: Boolean,
    val answerText: String,
    val question: Long,
    val studentAnswers: List<Long>,
) : Domain {
    companion object {
        fun new(
            correct: Boolean,
            answerText: String,
            question: Long,
        ) = AnswerOption(
            0,
            correct,
            answerText,
            question,
            mutableListOf(),
        )
    }
}
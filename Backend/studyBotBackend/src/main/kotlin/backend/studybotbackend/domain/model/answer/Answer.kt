package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Answer(
    val id: Long,
    val correct: String,
    val answerText: String,
    val question: Long
) : Domain {
    companion object {
        fun new(
            correct: String,
            answerText: String,
            question: Long
        ) = Answer(
            0,
            correct,
            answerText,
            question,
        )
    }
}
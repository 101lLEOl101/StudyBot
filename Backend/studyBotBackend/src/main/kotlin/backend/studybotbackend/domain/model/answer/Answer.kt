package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain
import backend.studybotbackend.domain.model.question.Question

data class Answer(
    val id: Long,
    val isStudentAnswer: Boolean,
    val correct: String,
    val answerText: String,
    val question: Long,
    val result: Long?
) : Domain {
    companion object {
        fun new(
            isStudentAnswer: Boolean,
            correct: String,
            answerText: String,
            question: Long,
            result: Long? = null
        ) = Answer(
            0,
            isStudentAnswer,
            correct,
            answerText,
            question,
            result,
        )
    }
}
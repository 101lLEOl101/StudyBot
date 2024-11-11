package backend.studybotbackend.domain.model.question

import backend.studybotbackend.domain.model.Domain

data class Question(
    val id: Long,
    val questionText: String,
    val questionType: QuestionType,
    val tests: List<Long>,
    val answers: List<Long>,
    val results: List<Long>,
) : Domain {
    companion object {
        fun new(
            questionText: String,
            questionType: QuestionType,
            tests: List<Long> = listOf(),
            answers: List<Long> = listOf(),
            results: List<Long> = listOf(),
        ) = Question(
            0,
            questionText,
            questionType,
            tests,
            answers,
            results,
        )
    }
}
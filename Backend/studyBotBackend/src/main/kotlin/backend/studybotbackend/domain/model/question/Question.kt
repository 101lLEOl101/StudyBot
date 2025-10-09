package backend.studybotbackend.domain.model.question

import backend.studybotbackend.domain.model.Domain

data class Question(
    val id: Long,
    val questionText: String,
    val questionType: QuestionType,
    val tests: List<Long>,
    val studentAnswers: List<Long>,
    val options: List<Long>,
) : Domain {
    companion object {
        fun new(
            questionText: String,
            questionType: QuestionType,
            tests: List<Long> = mutableListOf(),
            answers: List<Long> = mutableListOf(),
            options: List<Long> = mutableListOf()
        ) = Question(
            0,
            questionText,
            questionType,
            tests,
            answers,
            options,
        )
    }
}
package backend.studybotbackend.domain.request.question

import backend.studybotbackend.domain.model.question.QuestionType

class CreateQuestionRequest(
    val questionText: String,
    val questionType: QuestionType,
    val tests: List<Long>,
)
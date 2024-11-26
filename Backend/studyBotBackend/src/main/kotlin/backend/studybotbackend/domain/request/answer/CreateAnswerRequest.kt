package backend.studybotbackend.domain.request.answer

class CreateAnswerRequest (
    val isStudentAnswer: Boolean,
    val correct: String,
    val answerText: String,
    val question: Long,
)
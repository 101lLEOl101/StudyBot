package backend.studybotbackend.domain.request.answer

class CreateAnswerOptionRequest (
    val correct: Boolean,
    val answerText: String,
    val question: Long,
    
)
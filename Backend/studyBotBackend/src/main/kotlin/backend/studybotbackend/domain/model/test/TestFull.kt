package backend.studybotbackend.domain.model.test

import backend.studybotbackend.domain.model.question.QuestionType
import java.time.LocalDateTime

class TestFull(
    val id: Long = 0,
    val createTime: LocalDateTime,
    val expiresTime: LocalDateTime,
    val discipline: DisciplineFull,
    val testName: String,
    val questions: List<QuestionFull>,
    val results: List<Long> = listOf(),
)

class DisciplineFull(
    val id: Long,
    val disciplineName: String = "",
)

class QuestionFull(
    val id: Long = 0,
    val questionText: String,
    val questionType: QuestionType,
    val answers: List<AnswerFull> = listOf(),
)

class AnswerFull(
    val id: Long = 0,
    val isStudentAnswer: Boolean,
    val correct: String,
    val answerText: String,
)

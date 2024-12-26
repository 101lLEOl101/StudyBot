package backend.studybotbackend.domain.model.test

import backend.studybotbackend.domain.model.question.QuestionType
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class TestTree(
    val id: Long = 0,
    val createTime: LocalDateTime,
    val expiresTime: LocalDateTime,
    val discipline: DisciplineTree,
    val testName: String,
    val questions: List<QuestionTree>,
    val results: List<Long> = listOf(),
)

class DisciplineTree(
    val id: Long,
    val disciplineName: String,
)

class QuestionTree(
    val id: Long = 0,
    val questionText: String,
    val questionType: QuestionType,
    val answers: List<AnswerTree> = listOf(),
)

class AnswerTree(
    val id: Long = 0,
    val isStudentAnswer: Boolean,
    val correct: String,
    val answerText: String,
)

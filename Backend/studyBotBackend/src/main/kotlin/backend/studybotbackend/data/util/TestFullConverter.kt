package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.AnswerEntity
import backend.studybotbackend.data.entity.DisciplineEntity
import backend.studybotbackend.data.entity.QuestionEntity
import backend.studybotbackend.data.entity.TestEntity
import backend.studybotbackend.domain.model.answer.Answer
import backend.studybotbackend.domain.model.discipline.Discipline
import backend.studybotbackend.domain.model.question.Question
import backend.studybotbackend.domain.model.test.*
import org.springframework.stereotype.Component

@Component
interface TestFullConverter{

    fun TestEntity.toFull(): TestFull{
        return TestFull(
            testId,
            createTime,
            expiresTime,
            discipline.toFull(),
            testName,
            questions.map { it.toFull() },
            results.map { it.resultId }
        )
    }

    fun TestFull.toDomain(): Test{
        return Test.new(
            createTime,
            expiresTime,
            discipline.id,
            discipline.disciplineName,
            testName)
    }

    fun DisciplineEntity.toFull(): DisciplineFull{
        return DisciplineFull(
            disciplineId,
            disciplineName,
        )
    }

    fun DisciplineFull.toDomain(): Discipline{
        return Discipline.new(
            disciplineName
        )
    }

    fun QuestionEntity.toFull(): QuestionFull{
        return QuestionFull(
            questionId,
            questionText,
            questionType,
            answers.filter { !it.isStudentAnswer }.map { it.toFull() }
        )
    }

    fun QuestionFull.toDomain(tests: List<Long> = listOf()): Question{
        return Question.new(
            questionText,
            questionType,
            tests
        )
    }

    fun AnswerEntity.toFull(): AnswerFull{
        return AnswerFull(
            answerId,
            isStudentAnswer,
            correct,
            answerText,
        )
    }

    fun AnswerFull.toDomain(question: Long): Answer{
        return Answer.new(
            isStudentAnswer,
            correct,
            answerText,
            question,
        )
    }
}
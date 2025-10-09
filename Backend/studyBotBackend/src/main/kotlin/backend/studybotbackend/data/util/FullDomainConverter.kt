package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.AnswerOptionEntity
import backend.studybotbackend.data.entity.DisciplineEntity
import backend.studybotbackend.data.entity.QuestionEntity
import backend.studybotbackend.data.entity.TestEntity
import backend.studybotbackend.domain.model.answerOption.AnswerOption
import backend.studybotbackend.domain.model.discipline.Discipline
import backend.studybotbackend.domain.model.question.Question
import backend.studybotbackend.domain.model.test.*
import org.springframework.stereotype.Component

@Component
interface FullDomainConverter{

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
            options.map { it.toFull() }
        )
    }

    fun QuestionFull.toDomain(tests: List<Long> = mutableListOf()): Question{
        return Question.new(
            questionText,
            questionType,
            tests
        )
    }

    fun AnswerOptionEntity.toFull(): AnswerOptionFull{
        return AnswerOptionFull(
            answerOptionId,
            correct,
            answerText,
        )
    }

    fun AnswerOptionFull.toDomain(question: Long): AnswerOption{
        return AnswerOption.new(
            correct,
            answerText,
            question,
        )
    }
}
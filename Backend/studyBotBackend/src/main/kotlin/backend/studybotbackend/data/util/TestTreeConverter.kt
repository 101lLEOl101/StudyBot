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
interface TestTreeConverter{

    fun TestEntity.toTestTree(): TestTree{
        return TestTree(
            testId,
            createTime,
            expiresTime,
            discipline.toDisciplineTree(),
            testName,
            questions.map { it.toQuestionTree() },
            results.map { it.resultId }
        )
    }

    fun TestTree.toDomain(): Test{
        return Test.new(
            createTime,
            expiresTime,
            discipline.id,
            testName)
    }

    fun DisciplineEntity.toDisciplineTree(): DisciplineTree{
        return DisciplineTree(
            disciplineId,
            disciplineName,
        )
    }

    fun DisciplineTree.toDomain(): Discipline{
        return Discipline.new(
            disciplineName
        )
    }

    fun QuestionEntity.toQuestionTree(): QuestionTree{
        return QuestionTree(
            questionId,
            questionText,
            questionType,
            answers.filter { !it.isStudentAnswer }.map { it.toAnswerTree() }
        )
    }

    fun QuestionTree.toDomain(tests: List<Long> = listOf()): Question{
        return Question.new(
            questionText,
            questionType,
            tests
        )
    }

    fun AnswerEntity.toAnswerTree(): AnswerTree{
        return AnswerTree(
            answerId,
            isStudentAnswer,
            correct,
            answerText,
        )
    }

    fun AnswerTree.toDomain(question: Long): Answer{
        return Answer.new(
            isStudentAnswer,
            correct,
            answerText,
            question,
        )
    }
}
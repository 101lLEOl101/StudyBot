package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.AnswerDao
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.TestDao
import backend.studybotbackend.data.entity.QuestionEntity
import backend.studybotbackend.domain.model.question.Question
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class QuestionDomainConverter : DomainConverter<QuestionEntity, Question> {
    @Autowired
    private lateinit var testDao: TestDao

    @Autowired
    private lateinit var answerDao: AnswerDao

    @Autowired
    private lateinit var resultDao: ResultDao
    override fun Question.asDatabaseEntity(): QuestionEntity =
        QuestionEntity(
            questionText,
            questionType,
            testDao.findAllById(tests),
            answerDao.findAllById(answers),
            resultDao.findAllById(results),
        )

    override fun QuestionEntity.asDomain(): Question =
        Question(
            questionId,
            questionText,
            questionType,
            tests.map { it.testId },
            answers.map { it.answerId },
            results.map { it.resultId },
        )
}
package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.AnswerOptionDao
import backend.studybotbackend.data.dao.StudentAnswerDao
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
    private lateinit var answerOptionDao: AnswerOptionDao

    @Autowired
    private lateinit var studentAnswerDao: StudentAnswerDao

    override fun Question.asDatabaseEntity(): QuestionEntity =
        QuestionEntity(
            questionText,
            questionType,
            testDao.findAllById(tests),
            studentAnswerDao.findAllById(studentAnswers),
            answerOptionDao.findAllById(options),
        )

    override fun QuestionEntity.asDomain(): Question =
        Question(
            questionId,
            questionText,
            questionType,
            tests.map { it.testId },
            studentAnswers.map { it.studentAnswerId },
            options.map { it.answerOptionId }
        )
}
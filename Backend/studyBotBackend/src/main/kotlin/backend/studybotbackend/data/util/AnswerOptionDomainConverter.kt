package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.StudentAnswerDao
import backend.studybotbackend.data.entity.AnswerOptionEntity
import backend.studybotbackend.domain.model.answerOption.AnswerOption
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AnswerOptionDomainConverter : DomainConverter<AnswerOptionEntity, AnswerOption> {
    @Autowired
    private lateinit var questionDao: QuestionDao
    @Autowired
    private lateinit var resultDao: ResultDao
    @Autowired
    private lateinit var studentAnswerDao: StudentAnswerDao
    override fun AnswerOption.asDatabaseEntity(): AnswerOptionEntity =
        AnswerOptionEntity(
            correct,
            answerText,
            questionDao.findById(question).get(),
            studentAnswerDao.findAllById(studentAnswers),
        )

    override fun AnswerOptionEntity.asDomain(): AnswerOption =
        AnswerOption(
            answerOptionId,
            correct,
            answerText,
            question.questionId,
            studentAnswers.map { it.studentAnswerId }
        )
}
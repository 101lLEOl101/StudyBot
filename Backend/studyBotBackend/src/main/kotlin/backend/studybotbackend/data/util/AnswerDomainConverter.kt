package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.entity.AnswerEntity
import backend.studybotbackend.domain.model.answer.Answer
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AnswerDomainConverter: DomainConverter<AnswerEntity, Answer> {
    @Autowired
    private lateinit var questionDao: QuestionDao
    override fun Answer.asDatabaseEntity(): AnswerEntity =
        AnswerEntity(
                    correct,
                    answerText,
                    questionDao.findById(question).get(),
        )

    override fun AnswerEntity.asDomain(): Answer =
        Answer(
            answerId,
            correct,
            answerText,
            question.questionId
        )
}
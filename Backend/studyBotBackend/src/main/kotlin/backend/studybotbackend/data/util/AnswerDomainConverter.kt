package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.entity.AnswerEntity
import backend.studybotbackend.domain.model.answer.Answer
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrNull

@Component
class AnswerDomainConverter : DomainConverter<AnswerEntity, Answer> {
    @Autowired
    private lateinit var questionDao: QuestionDao
    @Autowired
    private lateinit var resultDao: ResultDao
    override fun Answer.asDatabaseEntity(): AnswerEntity =
        AnswerEntity(
            isStudentAnswer,
            correct,
            answerText,
            questionDao.findById(question).get(),
            if (result != null) resultDao.findById(result).getOrNull() else null,
        )

    override fun AnswerEntity.asDomain(): Answer =
        Answer(
            answerId,
            isStudentAnswer,
            correct,
            answerText,
            question.questionId,
            result?.resultId
        )
}
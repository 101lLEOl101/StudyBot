package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.AnswerOptionDao
import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.StudentAnswerDao
import backend.studybotbackend.data.entity.AnswerOptionEntity
import backend.studybotbackend.data.entity.StudentAnswerEntity
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.answerOption.AnswerOption
import backend.studybotbackend.domain.model.studentAnswer.StudentAnswer
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrElse

@Component
class StudentAnswerDomainConverter : DomainConverter<StudentAnswerEntity, StudentAnswer> {
    @Autowired
    private lateinit var questionDao: QuestionDao
    @Autowired
    private lateinit var resultDao: ResultDao
    @Autowired
    private lateinit var answerOptionDao: AnswerOptionDao
    override fun StudentAnswer.asDatabaseEntity(): StudentAnswerEntity =
        StudentAnswerEntity(
            percentage,
            questionDao.findById(question).get(),
            resultDao.findById(result).get(),
            answerOptionDao.findAllById(chosenOptions),
        )

    override fun StudentAnswerEntity.asDomain(): StudentAnswer =
        StudentAnswer(
            studentAnswerId,
            percentage,
            question.questionId,
            chosenOptions.map { it.answerOptionId }.toMutableList(),
            result.resultId,
            result.student.studentId,
        )
}
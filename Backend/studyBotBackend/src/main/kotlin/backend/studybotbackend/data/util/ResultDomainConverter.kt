package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.AnswerOptionDao
import backend.studybotbackend.data.dao.StudentAnswerDao
import backend.studybotbackend.data.dao.StudentDao
import backend.studybotbackend.data.dao.TestDao
import backend.studybotbackend.data.entity.ResultEntity
import backend.studybotbackend.domain.model.result.Result
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ResultDomainConverter : DomainConverter<ResultEntity, Result> {
    @Autowired
    private lateinit var studentDao: StudentDao
    @Autowired
    private lateinit var testDao: TestDao
    @Autowired
    private lateinit var answerOptionDao: AnswerOptionDao
    @Autowired
    private lateinit var studentAnswerDao: StudentAnswerDao

    override fun Result.asDatabaseEntity(): ResultEntity =
        ResultEntity(
            startTime,
            finishTime,
            studentDao.findById(student).get(),
            testDao.findById(test).get(),
            studentAnswerDao.findAllById(studentAnswers)
        )

    override fun ResultEntity.asDomain(): Result =
        Result(
            resultId,
            startTime,
            finishTime,
            student.chatId,
            test.testId,
            studentAnswers.map { it.studentAnswerId },
            student.firstName,
            student.lastName
        )
}
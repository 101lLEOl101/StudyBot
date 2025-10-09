package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.dao.StudentAnswerDao
import backend.studybotbackend.data.util.StudentAnswerDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.studentAnswer.StudentAnswer
import backend.studybotbackend.domain.service.StudentAnswerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class StudentAnswerServiceImpl : StudentAnswerService, StudentAnswerDomainConverter() {
    @Autowired
    private lateinit var studentAnswerDao: StudentAnswerDao
    @Autowired
    private lateinit var questiionDao: QuestionDao

    override fun getAnswerById(id: Long): State<StudentAnswer> {
        val entity = studentAnswerDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }


    override fun getAnswersByQuestion(id: Long): State<List<StudentAnswer>> {
        val entities = studentAnswerDao.findByQuestion(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createAnswer(studentAnswer: StudentAnswer): State<StudentAnswer> {
        val entity = studentAnswerDao.saveAndFlush(studentAnswer.asDatabaseEntity())
        entity.question.studentAnswers.add(entity)
        questiionDao.saveAndFlush(entity.question)
        return State.Success(entity.asDomain())
    }

    override fun getAllAnswers(): State<List<StudentAnswer>> {
        val entities = studentAnswerDao.findAll()
        return State.Success(entities.map { it.asDomain() })
    }

    override fun deleteAnswer(id: Long): State<Unit> {
        val entity = studentAnswerDao.findById(id).getOrElse { throw NotFoundException() }
        studentAnswerDao.delete(entity)
        return State.Success(Unit)
    }
}
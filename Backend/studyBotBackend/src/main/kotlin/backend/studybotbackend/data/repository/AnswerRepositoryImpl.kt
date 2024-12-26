package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.AnswerDao
import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.util.AnswerDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.answer.Answer
import backend.studybotbackend.domain.repository.AnswerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class AnswerRepositoryImpl : AnswerRepository, AnswerDomainConverter() {
    @Autowired
    private lateinit var answerDao: AnswerDao
    @Autowired
    private lateinit var questiionDao: QuestionDao

    override fun getAnswerById(id: Long): State<Answer> {
        val entity = answerDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getAnswersByResult(id: Long): State<List<Answer>> {
        val entities = answerDao.findByResult(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getRightAnswersByQuestion(id: Long): State<List<Answer>> {
        val entities = answerDao.findRightByQuestion(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getUserAnswersByQuestion(id: Long): State<List<Answer>> {
        val entities = answerDao.findUserByQuestion(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createAnswer(answer: Answer): State<Answer> {
        val entity = answerDao.saveAndFlush(answer.asDatabaseEntity())
        entity.question.answers = entity.question.answers.toMutableList()
        entity.question.answers.add(entity)
        questiionDao.saveAndFlush(entity.question)
        return State.Success(entity.asDomain())
    }

    override fun getAllAnswers(): State<List<Answer>> {
        val entities = answerDao.findAll()
        return State.Success(entities.map { it.asDomain() })
    }

    override fun deleteAnswer(id: Long): State<Unit> {
        val entity = answerDao.findById(id).getOrElse { throw NotFoundException() }
        answerDao.delete(entity)
        return State.Success(Unit)
    }
}
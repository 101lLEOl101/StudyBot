package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.AnswerOptionDao
import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.util.AnswerOptionDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.answerOption.AnswerOption
import backend.studybotbackend.domain.service.AnswerOptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class AnswerOptionServiceImpl : AnswerOptionService, AnswerOptionDomainConverter() {
    @Autowired
    private lateinit var answerOptionDao: AnswerOptionDao
    @Autowired
    private lateinit var questiionDao: QuestionDao

    override fun getOptionById(id: Long): State<AnswerOption> {
        val entity = answerOptionDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }


    override fun getOptionsByQuestion(id: Long): State<List<AnswerOption>> {
        val entities = answerOptionDao.findByQuestion(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createOption(answerOption: AnswerOption): State<AnswerOption> {
        val entity = answerOptionDao.save(answerOption.asDatabaseEntity())
        val options = entity.question.options.toMutableList()
        options.add(entity)
        answerOptionDao.save(entity)
        questiionDao.save(entity.question)
        return State.Success(entity.asDomain())
    }

    override fun getAllOptions(): State<List<AnswerOption>> {
        val entities = answerOptionDao.findAll()
        return State.Success(entities.map { it.asDomain() })
    }

    override fun deleteOption(id: Long): State<Unit> {
        val entity = answerOptionDao.findById(id).getOrElse { throw NotFoundException() }
        answerOptionDao.delete(entity)
        return State.Success(Unit)
    }
}
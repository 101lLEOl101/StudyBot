package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.entity.QuestionEntity
import backend.studybotbackend.data.util.QuestionDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.question.Question
import backend.studybotbackend.domain.repository.QuestionRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class QuestionRepositoryImpl(
    private val questionDao: QuestionDao,
) : QuestionRepository , QuestionDomainConverter(){
    override fun getQuestionById(id: Long): State<Question> {
        val entity = questionDao.findById(id).getOrElse { throw NotFoundException()}
        return State.Success(entity.asDomain())

    }

    override fun getQuestionsByTest(id: Long): State<List<Question>> {
        val entities = questionDao.findByTest(id).map { it.asDomain() }
        return State.Success(entities)
    }
}
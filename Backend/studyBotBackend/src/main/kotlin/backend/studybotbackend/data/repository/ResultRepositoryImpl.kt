package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.entity.ResultEntity
import backend.studybotbackend.data.util.ResultDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidRequestData
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.result.Result
import backend.studybotbackend.domain.repository.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

@Repository
class ResultRepositoryImpl : ResultRepository, ResultDomainConverter() {
    @Autowired
    private lateinit var resultDao: ResultDao


    override fun getResultById(id: Long): State<Result> {
        val entity = resultDao.findById(id).getOrElse { throw NotFoundException()}
        return State.Success(entity.asDomain())
    }

    override fun getResultsByStudent(id: Long): State<List<Result>> {
        val entities = resultDao.findByStudent(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getResultsByTest(id: Long): State<List<Result>> {
        val entities = resultDao.findByTest(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun startTest(chatId: Long, testId: Long): State<Result> {
        val entities = resultDao.findByTestAndStudent(chatId,testId)
        if(entities.any { it.finishTime == null}){
            throw InvalidRequestData("test have already started")
        }
        val domain = Result.new(
            LocalDateTime.now(),
            null,
            chatId,
            testId,
        )
        return State.Success(resultDao.save(domain.asDatabaseEntity()).asDomain())

    }
}
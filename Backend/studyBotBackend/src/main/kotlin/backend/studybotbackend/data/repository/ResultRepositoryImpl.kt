package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.util.ResultDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.result.Result
import backend.studybotbackend.domain.repository.ResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class ResultRepositoryImpl : ResultRepository, ResultDomainConverter() {
    @Autowired
    private lateinit var resultDao: ResultDao

    override fun getResultById(id: Long): State<Result> {
        val entity = resultDao.findById(id).getOrElse { throw NotFoundException()}
        return State.Success(entity.asDomain())
    }
}
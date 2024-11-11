package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.TestDao
import backend.studybotbackend.data.util.TestDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.repository.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class TestRepositoryImpl : TestRepository, TestDomainConverter() {
    @Autowired
    private lateinit var testDao: TestDao

    override fun getTestById(id: Long): State<Test> {
        val entity = testDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }
}
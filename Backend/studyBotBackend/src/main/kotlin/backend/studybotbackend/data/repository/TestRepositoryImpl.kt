package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.TestDao
import backend.studybotbackend.data.util.TestDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.repository.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class TestRepositoryImpl : TestRepository, TestDomainConverter() {
    @Autowired
    private lateinit var testDao: TestDao
    @Autowired
    private lateinit var resultDao: ResultDao

    override fun getTestById(id: Long): State<Test> {
        val entity = testDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getTestsByDiscipline(id: Long): State<List<Test>> {
        val entities = testDao.findByDiscipline(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getTestsByName(name: String): State<List<Test>> {
        val entities = testDao.findByTestNameContainsIgnoreCase(name).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createTest(test: Test): State<Test> {
        val entity = testDao.save(test.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun deleteTest(id: Long): State<Unit> {
        val entity = testDao.findById(id).getOrElse { throw NotFoundException() }
        resultDao.deleteAll(entity.results)
        testDao.delete(entity)
        return State.Success(Unit)
    }

    override fun getAllTests(): State<List<Test>> {
        val entities = testDao.findAll()
        return State.Success(entities.map { it.asDomain() })
    }
}
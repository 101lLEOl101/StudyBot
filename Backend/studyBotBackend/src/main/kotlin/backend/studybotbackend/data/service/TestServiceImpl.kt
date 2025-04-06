package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.TestDao
import backend.studybotbackend.data.entity.TestEntity
import backend.studybotbackend.data.util.TestDomainConverter
import backend.studybotbackend.data.util.FullDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.model.test.TestFull
import backend.studybotbackend.domain.service.AnswerService
import backend.studybotbackend.domain.service.QuestionService
import backend.studybotbackend.domain.service.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

@Service
class TestServiceImpl : TestService, TestDomainConverter(), FullDomainConverter {
    @Autowired
    private lateinit var testDao: TestDao

    @Autowired
    private lateinit var resultDao: ResultDao

    @Autowired
    private lateinit var questionService: QuestionService

    @Autowired
    private lateinit var answerService: AnswerService

    companion object {
        fun filterTest(test: TestEntity, isAvailable: Boolean): Boolean {
            return isAvailable == LocalDateTime.now().isBefore(test.expiresTime)
        }
    }

    override fun getTestById(id: Long): State<Test> {
        val entity = testDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getTestsByDiscipline(id: Long, isAvailable: Boolean): State<List<Test>> {
        val entities = testDao.findByDiscipline(id).filter { filterTest(it, isAvailable) }.map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getTestsByName(name: String, isAvailable: Boolean): State<List<Test>> {
        val entities = testDao.findByTestNameContainsIgnoreCase(name).filter { filterTest(it, isAvailable) }
            .map { it.asDomain() }
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

    override fun getAllTests(isAvailable: Boolean): State<List<Test>> {
        val entities = testDao.findAll()
        return State.Success(entities.filter { filterTest(it, isAvailable) }.map { it.asDomain() })
    }

    override fun getFullTest(id: Long): State<TestFull> {
        val entity = testDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.toFull())
    }

    override fun createFullTest(test: TestFull): State<TestFull> {
        val testEntity = createTest(test.toDomain()).data
        val testId = testEntity!!.id

        test.questions.forEach { questionTree ->
            val question = questionService.createQuestion(questionTree.toDomain(listOf(testId))).data
            questionTree.answers.forEach{answerTree ->
                answerService.createAnswer(answerTree.toDomain(question!!.id))
            }
        }

        return getFullTest(testId)
    }

    override fun getTestsByParty(id: Long, isAvailable: Boolean): State<List<Test>> {
        val entities = testDao.findByParty(id)
        return State.Success(entities.filter { filterTest(it, isAvailable) }.map { it.asDomain() })
    }
}
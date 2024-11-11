package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.DisciplineDao
import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.entity.TestEntity
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TestDomainConverter : DomainConverter<TestEntity, Test> {
    @Autowired
    private lateinit var disciplineDao: DisciplineDao
    @Autowired
    private lateinit var questionDao: QuestionDao
    @Autowired
    private lateinit var  resultDao: ResultDao

    override fun Test.asDatabaseEntity(): TestEntity =
        TestEntity(
            createTime,
            expiresTime,
            disciplineDao.findById(discipline).get(),
            testName,
            questionDao.findAllById(questions),
            resultDao.findAllById(results),
        )

    override fun TestEntity.asDomain(): Test =
    Test(testId,
        createTime,
        expiresTime,
        discipline.disciplineId,
        testName,
        questions.map { it.questionId },
        results.map { it.resultId },
        )

}
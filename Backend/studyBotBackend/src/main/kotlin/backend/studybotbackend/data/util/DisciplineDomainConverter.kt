package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.PartyDao
import backend.studybotbackend.data.dao.TestDao
import backend.studybotbackend.data.entity.DisciplineEntity
import backend.studybotbackend.domain.model.discipline.Discipline
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DisciplineDomainConverter : DomainConverter<DisciplineEntity, Discipline> {
    @Autowired
    private lateinit var testDao: TestDao
    @Autowired
    private lateinit var partyDao: PartyDao

    override fun Discipline.asDatabaseEntity(): DisciplineEntity =
        DisciplineEntity(
            disciplineName,
            testDao.findAllById(tests),
            partyDao.findAllById(partys)
        )

    override fun DisciplineEntity.asDomain(): Discipline =
        Discipline(
            disciplineId,
            disciplineName,
            tests.map { it.testId },
            partys.map { it.partyId },
        )
}
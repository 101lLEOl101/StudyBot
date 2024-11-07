package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.DisciplineEntity
import backend.studybotbackend.domain.model.answer.Discipline
import backend.studybotbackend.domain.util.DomainConverter

class DisciplineDomainConverter : DomainConverter<DisciplineEntity, Discipline> {
    override fun Discipline.asDatabaseEntity(): DisciplineEntity {
        TODO("Not yet implemented")
    }

    override fun DisciplineEntity.asDomain(): Discipline {
        TODO("Not yet implemented")
    }
}
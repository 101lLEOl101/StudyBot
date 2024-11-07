package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.UniversityEntity
import backend.studybotbackend.domain.model.answer.University
import backend.studybotbackend.domain.util.DomainConverter

class UnivercityDomainConverter : DomainConverter<UniversityEntity, University> {
    override fun University.asDatabaseEntity(): UniversityEntity {
        TODO("Not yet implemented")
    }

    override fun UniversityEntity.asDomain(): University {
        TODO("Not yet implemented")
    }
}
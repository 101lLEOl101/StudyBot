package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.domain.model.answer.Party
import backend.studybotbackend.domain.util.DomainConverter

class PartyDomainConverter : DomainConverter<PartyEntity, Party> {
    override fun Party.asDatabaseEntity(): PartyEntity {
        TODO("Not yet implemented")
    }

    override fun PartyEntity.asDomain(): Party {
        TODO("Not yet implemented")
    }
}
package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.stereotype.Component

@Component
class PartyDomainConverter : DomainConverter<PartyEntity, Party> {
    override fun Party.asDatabaseEntity(): PartyEntity =
        PartyEntity(
            partyName,
            listOf(),
            listOf(),
            listOf()
        )

    override fun PartyEntity.asDomain(): Party =
        Party(
            partyId,
            partyName,
            workers.map { it.workerId },
            students.map { it.chatId},
            disciplines.map { it.disciplineId },
        )
}
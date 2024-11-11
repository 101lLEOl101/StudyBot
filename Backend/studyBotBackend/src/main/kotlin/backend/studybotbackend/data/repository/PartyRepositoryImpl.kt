package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.PartyDao
import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.data.util.PartyDomainConverter
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.repository.PartyRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class PartyRepositoryImpl(
    private val partyDao: PartyDao
) : PartyRepository, PartyDomainConverter() {
    override fun getPartyById(id: Long): State<Party>{
        val entity: PartyEntity = partyDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

}
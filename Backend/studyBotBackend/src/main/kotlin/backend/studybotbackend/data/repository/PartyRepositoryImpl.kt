package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.PartyDao
import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.data.util.PartyDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.repository.PartyRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class PartyRepositoryImpl(
    private val partyDao: PartyDao,
) : PartyRepository, PartyDomainConverter() {
    override fun getPartyById(id: Long): State<Party> {
        val entity: PartyEntity = partyDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getPartysByStudentId(id: Long): State<List<Party>> {
        val entities = partyDao.findPartyEntitiesByStudentId(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getPartysByWorkerId(id: Long): State<List<Party>> {
        val entities = partyDao.findPartyEntitiesByWorkerId(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getPartysByDisciplineId(id: Long): State<List<Party>> {
        val entities = partyDao.findPartyEntitiesByDisciplineId(id).map { it.asDomain() }
        return State.Success(entities)
    }


}
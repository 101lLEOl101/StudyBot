package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.PartyDao
import backend.studybotbackend.data.dao.WorkerDao
import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.data.util.PartyDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidRequestData
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.repository.PartyRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

@Repository
class PartyRepositoryImpl(
    private val partyDao: PartyDao,
    private val workerDao: WorkerDao,
) : PartyRepository, PartyDomainConverter() {
    override fun getPartyById(id: Long): State<Party> {
        val entity: PartyEntity = partyDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getPartysByStudent(id: Long): State<List<Party>> {
        val entities = partyDao.findPartyEntitiesByStudentId(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getPartysByWorker(id: Long): State<List<Party>> {
        val entities = partyDao.findPartyEntitiesByWorkerId(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getPartysByDiscipline(id: Long): State<List<Party>> {
        val entities = partyDao.findPartyEntitiesByDisciplineId(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createParty(party: Party): State<Party> {
        val entity = partyDao.save(party.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun addWorker(partyId: Long, workerId: Long): State<Any> {
        val party = partyDao.findById(partyId).getOrElse { throw NotFoundException() }
        if (workerId in party.workers.map { it.workerId }){
            throw InvalidRequestData("Worker has already in party")
        }
        val worker = workerDao.findById(workerId).getOrElse { throw NotFoundException() }
        party.workers+=worker
        partyDao.save(party)
        return State.Success(LocalDateTime.now(),"worker has added")
    }

    override fun getAllPartys(): State<List<Party>> {
        val entities = partyDao.findAll()
        return State.Success(entities.map { it.asDomain() })
    }


}
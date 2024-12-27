package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.*
import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.data.util.PartyDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidRequestData
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.model.party.PartyInfo
import backend.studybotbackend.domain.repository.PartyRepository
import backend.studybotbackend.domain.repository.StudentRepository
import backend.studybotbackend.domain.repository.TestRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

@Repository
class PartyRepositoryImpl(
    private val partyDao: PartyDao,
    private val workerDao: WorkerDao,
    private val studentSubDao: StudentSubDao,
    private val studentRepository: StudentRepository,
    private val testRepository: TestRepository,
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

    override fun deleteParty(id: Long): State<Unit> {
        val entity = partyDao.findById(id).getOrElse { throw NotFoundException() }
        studentSubDao.deleteAll(entity.subs)
        partyDao.delete(entity)
        return State.Success(Unit)
    }

    override fun getPartyInfo(id: Long): State<PartyInfo> {
        val students = studentRepository.getStudentsByParty(id).data!!
        val tests = testRepository.getTestsByParty(id, true).data!!
        val partyInfo = PartyInfo(tests,students)
        return State.Success(partyInfo)
    }


}
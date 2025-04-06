package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.*
import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.data.util.PartyDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidRequestData
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.model.party.PartyInfo
import backend.studybotbackend.domain.service.PartyService
import backend.studybotbackend.domain.service.StudentService
import backend.studybotbackend.domain.service.TestService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrElse

@Service
class PartyServiceImpl(
    private val partyDao: PartyDao,
    private val workerDao: WorkerDao,
    private val studentSubDao: StudentSubDao,
    private val studentService: StudentService,
    private val testService: TestService,
) : PartyService, PartyDomainConverter() {
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
        val entity = partyDao.findById(id).getOrElse { throw NotFoundException() }
        val partyName = entity.partyName
        val students = studentService.getStudentsByParty(id).data!!
        val tests = testService.getTestsByParty(id, true).data!!
        val partyInfo = PartyInfo(id,partyName,tests,students)
        return State.Success(partyInfo)
    }


}
package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.DisciplineDao
import backend.studybotbackend.data.dao.StudentSubDao
import backend.studybotbackend.data.dao.WorkerDao
import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PartyDomainConverter : DomainConverter<PartyEntity, Party> {
    @Autowired
    private lateinit var workerDao: WorkerDao
    @Autowired
    private lateinit var disciplineDao: DisciplineDao
    @Autowired
    private lateinit var studentSubDao: StudentSubDao
    override fun Party.asDatabaseEntity(): PartyEntity =
        PartyEntity(
            partyName,
            workerDao.findAllById(workers),
            disciplineDao.findAllById(disciplines),
            studentSubDao.findAllById(subs),
        )

    override fun PartyEntity.asDomain(): Party =
        Party(
            partyId,
            partyName,
            workers.map { it.workerId },
            disciplines.map { it.disciplineId },
            subs.map { it.subId },
        )
}
package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.PartyDao
import backend.studybotbackend.data.entity.WorkerEntity
import backend.studybotbackend.domain.model.worker.Worker
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WorkerDomainConverter : DomainConverter<WorkerEntity, Worker> {

    @Autowired
    private lateinit var partyDao: PartyDao

    override fun Worker.asDatabaseEntity(): WorkerEntity =
        WorkerEntity(
            firstName,
            lastName,
            nickName,
            password,
            workerRole,
            partyDao.findAllById(partys)
        )

    override fun WorkerEntity.asDomain(): Worker =
        Worker(
            workerId,
            firstName,
            lastName,
            nickName,
            password,
            workerRole,
            partys.map { it.partyId }
        )
}
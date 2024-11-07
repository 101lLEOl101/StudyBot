package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.WorkerEntity
import backend.studybotbackend.domain.model.answer.Worker
import backend.studybotbackend.domain.util.DomainConverter

class WorkerDomainConverter : DomainConverter<WorkerEntity, Worker> {
    override fun Worker.asDatabaseEntity(): WorkerEntity {
        TODO("Not yet implemented")
    }

    override fun WorkerEntity.asDomain(): Worker {
        TODO("Not yet implemented")
    }
}
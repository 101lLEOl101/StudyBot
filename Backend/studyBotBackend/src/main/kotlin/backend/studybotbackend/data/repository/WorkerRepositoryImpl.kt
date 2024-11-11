package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.WorkerDao
import backend.studybotbackend.data.entity.WorkerEntity
import backend.studybotbackend.data.util.WorkerDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.worker.Worker
import backend.studybotbackend.domain.repository.WorkerRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class WorkerRepositoryImpl(
    private val workerDao: WorkerDao
) : WorkerRepository, WorkerDomainConverter() {
    override fun getWorkerById(id: Long): State<Worker> {
        val entity: WorkerEntity = workerDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

}
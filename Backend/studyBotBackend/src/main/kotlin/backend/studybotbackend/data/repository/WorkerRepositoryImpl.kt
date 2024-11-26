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

    override fun getWorkersByParty(id: Long): State<List<Worker>> {
        val entities = workerDao.findByParty(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createWorker(worker: Worker): State<Worker> {
        val entity = workerDao.save(worker.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun getAllWorkers(): State<List<Worker>> {
        return State.Success(workerDao.findAll().map { it.asDomain() })
    }

    override fun deleteWorker(id: Long): State<Unit> {
        val entity = workerDao.findById(id).getOrElse { throw NotFoundException() }
        workerDao.delete(entity)
        return State.Success(Unit)
    }

}
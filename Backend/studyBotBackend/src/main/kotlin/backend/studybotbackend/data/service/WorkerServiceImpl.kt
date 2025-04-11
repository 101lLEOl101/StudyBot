package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.WorkerDao
import backend.studybotbackend.data.entity.WorkerEntity
import backend.studybotbackend.data.util.WorkerDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidCredentials
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.worker.Worker
import backend.studybotbackend.domain.request.worker.UpdateWorkerRequest
import backend.studybotbackend.domain.service.WorkerService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class WorkerServiceImpl(
    private val workerDao: WorkerDao,
    private val passwordEncoder: PasswordEncoder,
) : WorkerService, WorkerDomainConverter() {
    override fun getWorkerById(id: Long): State<Worker> {
        val entity: WorkerEntity = workerDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getWorkersByParty(id: Long): State<List<Worker>> {
        val entities = workerDao.findByParty(id).map { it.asDomain() }
        return State.Success(entities)
    }


    override fun getWorkerByNickName(nickname: String): State<Worker> {
        val entity = workerDao.findByNickName(nickname).getOrElse { throw InvalidCredentials() }
        return State.Success(entity.asDomain())
    }

    override fun createWorker(worker: Worker): State<Worker> {
        worker.password = passwordEncoder.encode(worker.password)
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

    override fun signIn(nickname: String, password: String): State<Worker> {
        val entity = workerDao.signIn(nickname, password).getOrElse { throw InvalidCredentials() }
        return State.Success(entity.asDomain())
    }

    override fun updateWorker(req: UpdateWorkerRequest): State<Worker> {
        val entity = workerDao.findById(req.workerId).getOrElse { throw NotFoundException() }
        entity.firstName = req.firstName ?: entity.firstName
        entity.lastName = req.lastName ?: entity.lastName
        entity.nickName = req.nickName ?: entity.nickName
        entity.password = passwordEncoder.encode(req.password) ?: entity.password
        workerDao.save(entity)
        return State.Success(entity.asDomain())
    }



}
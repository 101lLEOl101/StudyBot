package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.worker.Worker

interface WorkerRepository {
    fun getWorkerById(id: Long): State<Worker>

    fun getWorkersByParty(id: Long): State<List<Worker>>

    fun createWorker(worker: Worker): State<Worker>

    fun getAllWorkers(): State<List<Worker>>
    fun deleteWorker(id: Long): State<Unit>
    fun signIn(nickname: String, password: String): State<Worker>
    fun updateWorker(
        workerId: Long,
        firstName: String?,
        lastName: String?,
        nickName: String?,
        password: String?
    ): State<Worker>

}
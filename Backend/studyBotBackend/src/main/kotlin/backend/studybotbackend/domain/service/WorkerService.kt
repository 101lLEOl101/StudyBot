package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.worker.Worker
import backend.studybotbackend.domain.request.worker.UpdateWorkerRequest

interface WorkerService {
    fun getWorkerById(id: Long): State<Worker>

    fun getWorkersByParty(id: Long): State<List<Worker>>

    fun getWorkerByNickName(nickname: String): State<Worker>

    fun createWorker(worker: Worker): State<Worker>

    fun getAllWorkers(): State<List<Worker>>
    fun deleteWorker(id: Long): State<Unit>
    fun signIn(nickname: String, password: String): State<Worker>
    fun updateWorker(
        request: UpdateWorkerRequest
    ): State<Worker>

}
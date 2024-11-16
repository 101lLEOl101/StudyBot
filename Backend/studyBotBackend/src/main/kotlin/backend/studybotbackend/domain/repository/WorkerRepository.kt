package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.worker.Worker

interface WorkerRepository {
    fun getWorkerById(id: Long): State<Worker>

    fun getWorkersByParty(id: Long): State<List<Worker>>

}
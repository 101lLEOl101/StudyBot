package backend.studybotbackend.domain.request.worker

import backend.studybotbackend.domain.model.worker.Role

class CreateWorkerRequest(
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val password: String,
    val workerRole: Role
)
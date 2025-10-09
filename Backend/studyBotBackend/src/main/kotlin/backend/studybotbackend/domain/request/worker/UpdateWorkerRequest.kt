package backend.studybotbackend.domain.request.worker

class UpdateWorkerRequest (
    val workerId: Long,
    val firstName: String?,
    val lastName: String?,
    val nickName: String?,
    val password: String?,
)
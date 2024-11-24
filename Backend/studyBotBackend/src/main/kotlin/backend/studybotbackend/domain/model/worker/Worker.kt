package backend.studybotbackend.domain.model.worker

import backend.studybotbackend.domain.model.Domain

data class Worker(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val password: String,
    val workerRole: Role,
    val partys: List<Long>,
) : Domain {
    companion object {
        fun new(
            firstName: String,
            lastName: String,
            nickName: String,
            password: String,
            workerRole: Role = Role.TEACHER,
            partys: List<Long> = listOf(),
        ) = Worker(
            0,
            firstName,
            lastName,
            nickName,
            password,
            workerRole,
            partys,
        )
    }
}
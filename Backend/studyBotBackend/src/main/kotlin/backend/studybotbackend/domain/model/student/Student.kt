package backend.studybotbackend.domain.model.student

import backend.studybotbackend.domain.model.Domain

data class Student(
    val id: Long,
    val nickname: String,
    val university: Long,
    val partys: List<Long>,
    val results: List<Long>,
) : Domain {
    companion object {
        fun new(
            nickname: String,
            university: Long,
            partys: List<Long> = listOf(),
            results: List<Long> = listOf(),
        ) = Student(
            0,
            nickname,
            university,
            partys,
            results,
        )
    }
}
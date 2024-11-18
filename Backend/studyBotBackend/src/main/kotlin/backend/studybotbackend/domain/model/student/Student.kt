package backend.studybotbackend.domain.model.student

import backend.studybotbackend.domain.model.Domain

data class Student(
    val id: Long,
    val nickname: String,
    val university: Long,
    val results: List<Long>,
    val subs: List<Long>,
) : Domain {
    companion object {
        fun new(
            nickname: String,
            university: Long,
            results: List<Long> = listOf(),
            subs: List<Long> = listOf(),
        ) = Student(
            0,
            nickname,
            university,
            results,
            subs,
        )
    }
}
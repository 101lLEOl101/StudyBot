package backend.studybotbackend.domain.model.student

import backend.studybotbackend.domain.model.Domain

data class Student(
    val chatId: Long,
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val university: Long,
    val results: List<Long>,
    val subs: List<Long>,
) : Domain {
    companion object {
        fun new(
            chatId: Long,
            firstName: String,
            lastName: String,
            nickname: String,
            university: Long,
            results: List<Long> = listOf(),
            subs: List<Long> = listOf(),
        ) = Student(
            chatId,
            firstName,
            lastName,
            nickname,
            university,
            results,
            subs,
        )
    }
}
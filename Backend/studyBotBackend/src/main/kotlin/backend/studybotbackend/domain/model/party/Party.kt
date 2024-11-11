package backend.studybotbackend.domain.model.party

import backend.studybotbackend.domain.model.Domain

data class Party(
    val id: Long,
    val partyName: String,
    val workers: List<Long>,
    val students: List<Long>,
    val disciplines: List<Long>,
) : Domain {
    companion object {
        fun new(
            partyName: String,
            workers: List<Long> = listOf(),
            students: List<Long> = listOf(),
            disciplines: List<Long> = listOf(),
        ) = Party(
            0,
            partyName,
            workers,
            students,
            disciplines,
        )
    }
}
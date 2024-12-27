package backend.studybotbackend.domain.model.party

import backend.studybotbackend.domain.model.Domain

data class Party(
    val id: Long,
    val studentsNum: Long,
    val partyName: String,
    val workers: List<Long>,
    val disciplines: List<Long>,
    val subs: List<Long>,
) : Domain {
    companion object {
        fun new(
            partyName: String,
            workers: List<Long> = listOf(),
            disciplines: List<Long> = listOf(),
            subs: List<Long> = listOf(),
        ) = Party(
            0,
            0,
            partyName,
            workers,
            disciplines,
            subs,
        )
    }
}
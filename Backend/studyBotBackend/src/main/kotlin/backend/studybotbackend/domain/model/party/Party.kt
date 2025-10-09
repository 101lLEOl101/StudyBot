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
            workers: List<Long> = mutableListOf(),
            disciplines: List<Long> = mutableListOf(),
            subs: List<Long> = mutableListOf(),
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
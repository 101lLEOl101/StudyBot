package backend.studybotbackend.domain.model.discipline

import backend.studybotbackend.domain.model.Domain

data class Discipline(
    val id: Long,
    val disciplineName: String,
    val tests: List<Long>,
    val partys: List<Long>,
) : Domain {
    companion object {
        fun new(
            disciplineName: String,
            tests: List<Long> = listOf(),
            partys: List<Long> = listOf(),
        ) = Discipline(
            0,
            disciplineName,
            tests,
            partys,
        )
    }
}
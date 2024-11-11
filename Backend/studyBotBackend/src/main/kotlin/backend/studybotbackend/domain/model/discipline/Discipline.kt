package backend.studybotbackend.domain.model.discipline

import backend.studybotbackend.domain.model.Domain

data class Discipline(
    val id: Long,
    val disciplineName: String,
    val tests: List<Long> = listOf(),
    val partys: List<Long> = listOf(),
) : Domain {
    companion object {
        fun new(
            disciplineName: String,
            tests: List<Long>,
            partys: List<Long>,
        ) = Discipline(
            0,
            disciplineName,
            tests,
            partys,
        )
    }
}
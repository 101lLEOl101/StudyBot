package backend.studybotbackend.domain.model.univercity

import backend.studybotbackend.domain.model.Domain

data class University(
    val id: Long,
    val universityName: String,
    val students: List<Long>,
) : Domain {
    companion object {
        fun new(
            universityName: String,
            students: List<Long> = listOf(),
        ) = University(
            0,
            universityName,
            students,
        )
    }
}
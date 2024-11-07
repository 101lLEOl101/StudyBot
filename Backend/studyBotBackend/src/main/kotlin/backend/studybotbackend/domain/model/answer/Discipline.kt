package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Discipline(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = Discipline(
            0,
        )
    }
}
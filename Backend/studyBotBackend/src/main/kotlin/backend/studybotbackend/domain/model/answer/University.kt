package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class University(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = University(
            0,
        )
    }
}
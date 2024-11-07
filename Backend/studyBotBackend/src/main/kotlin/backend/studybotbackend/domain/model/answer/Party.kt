package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Party(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = Party(
            0,
        )
    }
}
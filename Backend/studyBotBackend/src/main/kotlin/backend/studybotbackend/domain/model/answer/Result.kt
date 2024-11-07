package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Result(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = Result(
            0,
        )
    }
}
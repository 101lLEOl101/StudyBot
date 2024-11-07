package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Answer(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = Answer(
            0,
        )
    }
}
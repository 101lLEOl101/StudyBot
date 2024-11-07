package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Question(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = Question(
            0,
        )
    }
}
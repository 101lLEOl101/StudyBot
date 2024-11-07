package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Test(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = Test(
            0,
        )
    }
}
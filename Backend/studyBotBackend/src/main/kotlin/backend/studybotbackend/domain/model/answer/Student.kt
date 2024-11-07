package backend.studybotbackend.domain.model.answer

import backend.studybotbackend.domain.model.Domain

data class Student(
    val id: Long,
) : Domain {
    companion object {
        fun new(

        ) = Student(
            0,
        )
    }
}
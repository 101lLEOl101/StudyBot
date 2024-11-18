package backend.studybotbackend.domain.model.studentSub

import backend.studybotbackend.domain.model.Domain
import backend.studybotbackend.domain.model.student.Student

data class StudentSub(
    val id: Long,
    val status: Status,
    val student: Long,
    val party: Long,

): Domain{
    companion object{
        fun new(
            status: Status = Status.NOT_APROVED,
            student: Long,
            party: Long,
        ) = StudentSub(
            0,
            status,
            student,
            party,
        )
    }
}

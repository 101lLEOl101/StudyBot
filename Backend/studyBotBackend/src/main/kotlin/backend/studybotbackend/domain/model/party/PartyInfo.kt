package backend.studybotbackend.domain.model.party

import backend.studybotbackend.domain.model.student.Student
import backend.studybotbackend.domain.model.test.Test

class PartyInfo(
    val id: Long,
    val name: String,
    val activeTests: List<Test>,
    val students: List<Student>,
)
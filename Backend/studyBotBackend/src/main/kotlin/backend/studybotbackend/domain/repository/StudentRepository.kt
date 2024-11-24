package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.student.Student

interface StudentRepository {
    fun getStudentById(id: Long): State<Student>

    fun getStudentsByUnivercity(id: Long): State<List<Student>>

    fun getStudentsByParty(id: Long): State<List<Student>>

    fun createStudent(student: Student): State<Student>
}
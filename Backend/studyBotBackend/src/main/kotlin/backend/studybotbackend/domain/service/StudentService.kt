package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.student.Student

interface StudentService {
    fun getStudentById(id: Long): State<Student>

    fun getStudentsByUnivercity(id: Long): State<List<Student>>

    fun getStudentsByParty(id: Long): State<List<Student>>

    fun createStudent(student: Student): State<Student>
    fun deleteStudent(id: Long): State<Unit>

    fun getAllStudents(): State<List<Student>>
    fun getStudentsByTest(id: Long): State<List<Student>>
}
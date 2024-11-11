package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.student.Student

interface StudentRepository {
    fun getStudentById(id: Long): State<Student>
}
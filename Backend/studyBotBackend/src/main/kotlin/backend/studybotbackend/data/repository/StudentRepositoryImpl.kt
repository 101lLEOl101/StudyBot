package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.StudentDao
import backend.studybotbackend.data.util.StudentDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.student.Student
import backend.studybotbackend.domain.repository.StudentRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class StudentRepositoryImpl(
    private val studentDao: StudentDao
) : StudentRepository, StudentDomainConverter() {
    override fun getStudentById(id: Long): State<Student> {
        val entity = studentDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getStudentsByUnivercity(id: Long): State<List<Student>> {
        val entities = studentDao.findByUniversity(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getStudentsByParty(id: Long): State<List<Student>> {
        val entities = studentDao.findByParty(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createStudent(student: Student): State<Student> {
        val entity = studentDao.save(student.asDatabaseEntity())

        return State.Success(entity.asDomain())
    }
}
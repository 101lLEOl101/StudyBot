package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.StudentDao
import backend.studybotbackend.data.dao.StudentSubDao
import backend.studybotbackend.data.util.StudentDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.student.Student
import backend.studybotbackend.domain.service.StudentService
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class StudentServiceImpl(
    private val studentDao: StudentDao,
    private val studentSubDao: StudentSubDao,
    private val resultDao: ResultDao
) : StudentService, StudentDomainConverter() {
    override fun getStudentById(id: Long): State<Student> {
        val entity = studentDao.findByChatId(id).getOrElse { throw NotFoundException() }
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

    override fun deleteStudent(id: Long): State<Unit> {
        val entity = studentDao.findByChatId(id).getOrElse { throw NotFoundException() }
        resultDao.deleteAll(entity.results)
        studentSubDao.deleteAll(entity.subs)
        studentDao.delete(entity)
        return State.Success(Unit)
    }

    override fun getAllStudents(): State<List<Student>> {
        return State.Success(studentDao.findAll().map { it.asDomain() })
    }
}
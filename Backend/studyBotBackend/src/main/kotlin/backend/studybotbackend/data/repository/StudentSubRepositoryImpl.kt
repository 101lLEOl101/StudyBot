package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.StudentSubDao
import backend.studybotbackend.data.util.StudentSubDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.studentSub.StudentSub
import backend.studybotbackend.domain.repository.StudentSubRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class StudentSubRepositoryImpl(
    private val studentSubDao: StudentSubDao
): StudentSubRepository, StudentSubDomainConverter() {
    override fun getStudentSubById(id: Long): State<StudentSub> {
        val entity = studentSubDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getStudentSubsByParty(id: Long): State<List<StudentSub>> {
        val entities = studentSubDao.findByParty(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getStudentSubsByStudent(id: Long): State<List<StudentSub>> {
        val entities = studentSubDao.findByStudent(id).map { it.asDomain() }
        return State.Success(entities)
    }

}
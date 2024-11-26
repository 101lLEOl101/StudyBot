package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.StudentDao
import backend.studybotbackend.data.dao.StudentSubDao
import backend.studybotbackend.data.util.StudentSubDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidRequestData
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.studentSub.Status
import backend.studybotbackend.domain.model.studentSub.StudentSub
import backend.studybotbackend.domain.repository.StudentSubRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class StudentSubRepositoryImpl(
    private val studentSubDao: StudentSubDao,
    private val studentDao: StudentDao,
) : StudentSubRepository, StudentSubDomainConverter() {
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

    override fun createSubscribe(chatId: Long, partyId: Long): State<StudentSub> {
        val subs = studentSubDao.findByParty(partyId)
        if (subs.any { it.student.chatId == chatId }) {
            throw InvalidRequestData("Subscribe is already exist")
        }
        val studentId = studentDao.getIdByChatId(chatId)
        val sub = StudentSub.new(Status.NOT_CONSIDERED, studentId, partyId)
        val entity = studentSubDao.save(sub.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun acceptSub(subId: Long): State<Boolean> {
        val sub = studentSubDao.findById(subId).getOrElse { throw NotFoundException() }
        when (sub.status) {
            Status.APROVED -> {throw InvalidRequestData("Sub is aproved")}
            Status.NOT_APROVED -> {throw InvalidRequestData("Sub isn't aproved")}
            Status.NOT_CONSIDERED -> {
                sub.status = Status.APROVED
                studentSubDao.save(sub)
            }

        }
        return State.Success(true)
    }

}
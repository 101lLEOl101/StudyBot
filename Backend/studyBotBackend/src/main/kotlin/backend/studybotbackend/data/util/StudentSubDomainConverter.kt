package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.PartyDao
import backend.studybotbackend.data.dao.StudentDao
import backend.studybotbackend.data.entity.StudentSubEntity
import backend.studybotbackend.domain.model.studentSub.StudentSub
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StudentSubDomainConverter : DomainConverter<StudentSubEntity, StudentSub> {
    @Autowired
    private lateinit var studentDao: StudentDao

    @Autowired
    private lateinit var partyDao: PartyDao

    override fun StudentSub.asDatabaseEntity(): StudentSubEntity =
        StudentSubEntity(
            status,
            studentDao.findById(student).get(),
            partyDao.findById(party).get(),
        )

    override fun StudentSubEntity.asDomain(): StudentSub =
        StudentSub(
            subId,
            status,
            student.chatId,
            party.partyId,
        )
}
package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.StudentDao
import backend.studybotbackend.data.entity.UniversityEntity
import backend.studybotbackend.domain.model.univercity.University
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UnivercityDomainConverter : DomainConverter<UniversityEntity, University> {
    @Autowired
    private lateinit var studentDao: StudentDao
    override fun University.asDatabaseEntity(): UniversityEntity =
        UniversityEntity(
            universityName,
            studentDao.findAllById(students),
        )

override fun UniversityEntity.asDomain(): University =
    University(universityId,
        universityName,
        students.map { it.chatId },
        )
}
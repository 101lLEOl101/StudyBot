package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.PartyDao
import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.UniversityDao
import backend.studybotbackend.data.entity.StudentEntity
import backend.studybotbackend.domain.model.student.Student
import backend.studybotbackend.domain.util.DomainConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StudentDomainConverter : DomainConverter<StudentEntity, Student> {
    @Autowired
    private lateinit var universityDao: UniversityDao
    @Autowired
    private lateinit var partyDao: PartyDao
    @Autowired
    private lateinit var resultDao: ResultDao

    override fun Student.asDatabaseEntity(): StudentEntity =
        StudentEntity(
            nickname,
            universityDao.findById(university).get(),
            partyDao.findAllById(partys),
            resultDao.findAllById(results)
        )
    override fun StudentEntity.asDomain(): Student =
        Student(chatId,
            nickname,
            university.universityId,
            partys.map { it.partyId },
            results.map{it.resultId},
            )
}
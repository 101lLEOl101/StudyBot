package backend.studybotbackend.data.util

import backend.studybotbackend.data.dao.ResultDao
import backend.studybotbackend.data.dao.StudentSubDao
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
    private lateinit var studentSubDao: StudentSubDao
    @Autowired
    private lateinit var resultDao: ResultDao

    override fun Student.asDatabaseEntity(): StudentEntity =
        StudentEntity(
            chatId,
            firstName,
            lastName,
            nickname,
            universityDao.findById(university).get(),
            resultDao.findAllById(results),
            studentSubDao.findAllById(subs),

        )
    override fun StudentEntity.asDomain(): Student =
        Student(chatId,
            firstName,
            lastName,
            nickname,
            university.universityId,
            results.map{it.resultId},
            subs.map { it.subId },
            )
}
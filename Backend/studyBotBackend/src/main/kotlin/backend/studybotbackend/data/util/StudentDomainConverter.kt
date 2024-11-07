package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.StudentEntity
import backend.studybotbackend.domain.model.answer.Student
import backend.studybotbackend.domain.util.DomainConverter

class StudentDomainConverter : DomainConverter<StudentEntity, Student> {
    override fun Student.asDatabaseEntity(): StudentEntity {
        TODO("Not yet implemented")
    }

    override fun StudentEntity.asDomain(): Student {
        TODO("Not yet implemented")
    }
}
package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface StudentDao: JpaRepository<StudentEntity, Long> {
}
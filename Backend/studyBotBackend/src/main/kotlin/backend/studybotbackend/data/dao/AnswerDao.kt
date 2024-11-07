package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerDao: JpaRepository<AnswerEntity,Long> {
}
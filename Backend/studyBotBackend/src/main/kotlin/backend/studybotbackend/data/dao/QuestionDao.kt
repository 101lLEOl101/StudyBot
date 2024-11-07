package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionDao : JpaRepository<QuestionEntity, Long> {
}
package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.AnswerOptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AnswerOptionDao : JpaRepository<AnswerOptionEntity, Long> {

    @Query("select a from AnswerOptionEntity a where a.question.questionId = :id")
    fun findByQuestion(@Param("id") id: Long): List<AnswerOptionEntity>


}
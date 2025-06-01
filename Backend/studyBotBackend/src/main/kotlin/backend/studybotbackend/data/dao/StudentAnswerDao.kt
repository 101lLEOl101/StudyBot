package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.AnswerOptionEntity
import backend.studybotbackend.data.entity.StudentAnswerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface StudentAnswerDao : JpaRepository<StudentAnswerEntity, Long> {

    @Query("select a from StudentAnswerEntity a where a.question.questionId = :id")
    fun findByQuestion(@Param("id") id: Long): List<StudentAnswerEntity>


}
package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.AnswerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface AnswerDao : JpaRepository<AnswerEntity, Long> {

    @Query("select a from AnswerEntity a where a.result.resultId = :id")
    fun findByResult(@Param("id") id: Long): List<AnswerEntity>

    @Query("select a from AnswerEntity a where a.question.questionId = :id and a.isStudentAnswer = false")
    fun findRightByQuestion(@Param("id") id: Long): List<AnswerEntity>

    @Query("select a from AnswerEntity a where a.question.questionId =:id and a.isStudentAnswer = true ")
    fun findUserByQuestion(@Param("id") id: Long): List<AnswerEntity>

}
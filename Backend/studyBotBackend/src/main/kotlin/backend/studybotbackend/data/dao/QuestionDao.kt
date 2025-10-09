package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.QuestionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface QuestionDao : JpaRepository<QuestionEntity, Long> {

    @Query("select q from  QuestionEntity q INNER JOIN q.tests t where t.testId = :id")
    fun findByTest(@Param("id") id: Long): List<QuestionEntity>


}
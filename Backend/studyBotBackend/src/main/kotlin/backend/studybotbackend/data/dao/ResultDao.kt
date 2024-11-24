package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.ResultEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ResultDao : JpaRepository<ResultEntity, Long> {

    @Query("select r from ResultEntity r where r.student.chatId = :id")
    fun findByStudent(@Param("id") id: Long): List<ResultEntity>

    @Query("select r from ResultEntity r where r.test.testId = :id")
    fun findByTest(@Param("id") id: Long): List<ResultEntity>

    @Query("select r from ResultEntity r where " +
            "r.student.chatId = :chat_id " +
            "and r.test.testId = :test_id")
    fun findByTestAndStudent(@Param("chat_id") chatId: Long, @Param("test_id") testId: Long): List<ResultEntity>

}
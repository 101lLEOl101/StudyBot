package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.StudentSubEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface StudentSubDao: JpaRepository<StudentSubEntity, Long> {

    @Query("select s from StudentSubEntity s where s.party.partyId = :id")
    fun findByParty(@Param("id") party: Long): List<StudentSubEntity>

    @Query("select s from StudentSubEntity s where s.student.chatId = :id")
    fun findByStudent(@Param("id") student: Long): List<StudentSubEntity>
}
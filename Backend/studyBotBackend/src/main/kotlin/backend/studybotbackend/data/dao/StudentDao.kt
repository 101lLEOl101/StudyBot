package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface StudentDao: JpaRepository<StudentEntity, Long> {


    @Query("select s from StudentEntity s where s.university.universityId = :id")
    fun findByUniversity(@Param("id") id: Long): List<StudentEntity>

    @Query("select st from StudentEntity st INNER JOIN st.subs su " +
            "where su.status = backend.studybotbackend.domain.model.studentSub.Status.APROVED" +
            " and su.party.partyId = :id")
    fun findByParty(@Param("id") id: Long): List<StudentEntity>



}
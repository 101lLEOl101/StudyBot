package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.PartyEntity
import backend.studybotbackend.data.entity.StudentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PartyDao: JpaRepository<PartyEntity,Long> {

    @Query("select p from PartyEntity p INNER JOIN p.students s where s.chatId = :id")
    fun findPartyEntitiesByStudentId(@Param("id")  id: Long) : List<PartyEntity>

    @Query("select p from PartyEntity p INNER JOIN p.workers w where w.workerId = :id")
    fun findPartyEntitiesByWorkerId(@Param("id")  id: Long) : List<PartyEntity>
    @Query("select p from PartyEntity p INNER JOIN p.disciplines w where w.disciplineId = :id")
    fun findPartyEntitiesByDisciplineId(@Param("id")  id: Long) : List<PartyEntity>

}
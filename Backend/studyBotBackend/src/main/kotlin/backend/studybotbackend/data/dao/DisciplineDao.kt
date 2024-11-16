package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.DisciplineEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DisciplineDao: JpaRepository<DisciplineEntity,Long> {

    @Query("select d from DisciplineEntity d INNER JOIN d.tests t where t.testId = :id")
    fun findByTest(@Param("id") id: Long): List<DisciplineEntity>

    @Query("select d from DisciplineEntity d INNER JOIN d.partys p where p.partyId = :id")
    fun findByParty(@Param("id") id: Long): List<DisciplineEntity>


}
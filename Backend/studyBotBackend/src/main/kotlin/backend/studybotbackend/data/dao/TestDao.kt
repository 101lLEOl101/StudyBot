package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.TestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TestDao: JpaRepository<TestEntity, Long> {

    @Query("select t from TestEntity t where t.discipline.disciplineId = :id")
    fun findByDiscipline(@Param("id") discipline: Long): List<TestEntity>

    fun findByTestNameContainsIgnoreCase(name: String): List<TestEntity>

    @Query("select t from TestEntity t INNER JOIN t.discipline.partys p where p.partyId =:id")
    fun findByParty(@Param("id") party: Long)

}
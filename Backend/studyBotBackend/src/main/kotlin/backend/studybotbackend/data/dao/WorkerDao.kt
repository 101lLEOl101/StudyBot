package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.WorkerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface WorkerDao: JpaRepository<WorkerEntity, Long> {

    @Query("select w from WorkerEntity w INNER JOIN w.partys p where p.partyId = :id ")
    fun findByParty(@Param("id") id: Long): List<WorkerEntity>
}
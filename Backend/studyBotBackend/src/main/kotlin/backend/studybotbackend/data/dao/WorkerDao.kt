package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.WorkerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface WorkerDao : JpaRepository<WorkerEntity, Long> {

    @Query("select w from WorkerEntity w INNER JOIN w.partys p where p.partyId = :id ")
    fun findByParty(@Param("id") id: Long): List<WorkerEntity>

    @Query("select w from WorkerEntity w where w.nickName = :nickname and w.password = :password")
    fun signIn(@Param("nickname") nickname: String, @Param("password") password: String): Optional<WorkerEntity>
}
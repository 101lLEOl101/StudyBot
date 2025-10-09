package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.UniversityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UniversityDao : JpaRepository<UniversityEntity, Long> {

    @Query("select u from UniversityEntity u INNER JOIN u.students s where s.chatId = :id")
    fun findByStudent(@Param("id") id: Long): Optional<UniversityEntity>
}
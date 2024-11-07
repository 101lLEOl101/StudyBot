package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.DisciplineEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DisciplineDao: JpaRepository<DisciplineEntity,Long> {
}
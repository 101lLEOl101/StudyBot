package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.WorkerEntity
import org.springframework.data.jpa.repository.JpaRepository

interface WorkerDao: JpaRepository<WorkerEntity, Long> {
}
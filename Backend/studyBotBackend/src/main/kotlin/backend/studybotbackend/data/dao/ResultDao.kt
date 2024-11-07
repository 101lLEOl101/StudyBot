package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.ResultEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ResultDao: JpaRepository<ResultEntity, Long> {
}
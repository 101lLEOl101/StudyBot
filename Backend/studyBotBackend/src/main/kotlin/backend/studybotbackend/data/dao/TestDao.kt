package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.TestEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TestDao: JpaRepository<TestEntity, Long> {
}
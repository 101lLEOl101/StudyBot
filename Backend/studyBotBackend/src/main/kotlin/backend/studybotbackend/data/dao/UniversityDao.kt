package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.UniversityEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UniversityDao: JpaRepository<UniversityEntity,Long> {
}
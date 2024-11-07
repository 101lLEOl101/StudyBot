package backend.studybotbackend.data.dao

import backend.studybotbackend.data.entity.PartyEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PartyDao: JpaRepository<PartyEntity,Long> {
}
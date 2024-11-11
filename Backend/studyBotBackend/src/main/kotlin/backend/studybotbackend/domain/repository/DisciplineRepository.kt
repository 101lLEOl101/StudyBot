package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.discipline.Discipline

interface DisciplineRepository {
    fun getDisciplineById(id: Long): State<Discipline>
}
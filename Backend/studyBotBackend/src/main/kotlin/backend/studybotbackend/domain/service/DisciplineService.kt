package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.discipline.Discipline

interface DisciplineService {
    fun getDisciplineById(id: Long): State<Discipline>

    fun getDisciplinesByTest(id: Long): State<List<Discipline>>

    fun getDisciplinesByParty(id: Long): State<List<Discipline>>

    fun createDiscipline(discipline: Discipline): State<Discipline>

    fun getAllDisciplines(): State<List<Discipline>>

    fun deleteDiscipline(id: Long): State<Unit>
    fun getDisciplinesByStudent(id: Long): State<List<Discipline>>

}
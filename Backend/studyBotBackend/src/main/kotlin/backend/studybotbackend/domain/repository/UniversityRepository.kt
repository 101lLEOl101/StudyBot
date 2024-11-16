package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.univercity.University

interface UniversityRepository {

    fun getUniversityById(id: Long): State<University>

    fun getUniversityByStudent(id: Long): State<University>
}
package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.univercity.University

interface UniversityService {

    fun getUniversityById(id: Long): State<University>

    fun getUniversityByStudent(id: Long): State<University>

    fun createUnivercity(university: University): State<University>
    fun deleteUniversity(id: Long): State<Unit>
    fun getAllUnivercities(): State<List<University>>
}
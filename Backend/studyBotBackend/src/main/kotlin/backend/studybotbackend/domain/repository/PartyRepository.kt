package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.entity.StudentEntity
import backend.studybotbackend.domain.model.party.Party

interface PartyRepository {
    fun getPartyById(id: Long): State<Party>

    fun getPartysByStudentId(id: Long): State<List<Party>>

    fun getPartysByWorkerId(id: Long): State<List<Party>>

    fun getPartysByDisciplineId(id: Long): State<List<Party>>

    fun createParty(party: Party): State<Party>


}
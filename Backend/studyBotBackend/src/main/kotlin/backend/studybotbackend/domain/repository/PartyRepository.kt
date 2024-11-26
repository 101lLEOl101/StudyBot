package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.party.Party

interface PartyRepository {
    fun getPartyById(id: Long): State<Party>

    fun getPartysByStudent(id: Long): State<List<Party>>

    fun getPartysByWorker(id: Long): State<List<Party>>

    fun getPartysByDiscipline(id: Long): State<List<Party>>

    fun createParty(party: Party): State<Party>

    fun addWorker(partyId: Long, workerId: Long): State<Any>

    fun getAllPartys(): State<List<Party>>
    fun deleteParty(id: Long): State<Unit>

}
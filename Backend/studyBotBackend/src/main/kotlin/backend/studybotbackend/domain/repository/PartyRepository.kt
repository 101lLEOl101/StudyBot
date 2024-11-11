package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.party.Party

interface PartyRepository {
    fun getPartyById(id: Long): State<Party>
}
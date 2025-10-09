package backend.studybotbackend.domain.request.party

class CreatePartyRequest(
    val partyName: String,
    val workers: List<Long>,
    val disciplines: List<Long>,
)
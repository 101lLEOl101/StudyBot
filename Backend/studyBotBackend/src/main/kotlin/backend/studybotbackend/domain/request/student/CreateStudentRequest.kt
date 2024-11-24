package backend.studybotbackend.domain.request.student

import backend.studybotbackend.domain.request.univercity.CreateUnivercityRequest

class CreateStudentRequest(
    val chatId: Long,
    val nickName: String,
    val univercity: Long
)
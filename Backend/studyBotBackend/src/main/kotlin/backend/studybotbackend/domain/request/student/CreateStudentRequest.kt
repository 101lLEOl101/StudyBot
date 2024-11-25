package backend.studybotbackend.domain.request.student

import backend.studybotbackend.domain.request.univercity.CreateUnivercityRequest

class CreateStudentRequest(
    val chatId: Long,
    val firstName: String,
    val lastName: String,
    val nickName: String,
    val univercity: Long
)
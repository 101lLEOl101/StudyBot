package backend.studybotbackend.domain.request.test

import java.time.LocalDateTime

class CreateTestRequest(
    val expiresTime: LocalDateTime = LocalDateTime.now(),
    val discipline: Long,
    val testName: String,
)
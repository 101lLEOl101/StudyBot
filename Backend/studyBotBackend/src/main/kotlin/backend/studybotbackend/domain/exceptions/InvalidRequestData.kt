package backend.studybotbackend.domain.exceptions

import com.fasterxml.jackson.annotation.JsonPropertyOrder

class InvalidRequestData(
    description: String? = null
): BaseException(
    message = "invalid request data",
    statusCode = 406,
    description = description
)
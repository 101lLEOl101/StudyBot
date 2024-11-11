package backend.studybotbackend.domain.exceptions

class ServerError(
    description: String? = null
): BaseException(
    message = "Some error",
    statusCode = 500,
    description = description
)
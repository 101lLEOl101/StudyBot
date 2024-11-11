package backend.studybotbackend.domain.exceptions

class NotFoundException(
    description: String? = null
): BaseException(
    message = "not found",
    statusCode = 404,
    description = description
)
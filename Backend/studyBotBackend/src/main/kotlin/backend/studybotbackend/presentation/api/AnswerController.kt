package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.repository.AnswerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.ANSWER_API)
class AnswerController(
    private val answerRepository: AnswerRepository
) {
    @ExceptionHandler(Exception::class, BaseException::class)
    fun exceptionHandler(e: Exception): ResponseEntity<Any> {
        return when (e) {
            is BaseException -> {
                ResponseEntity.status(e.statusCode).body(e)
            }
            else -> {
                ResponseEntity.status(500).body(ServerError(description = e.message))
            }
        }
    }

    @GetMapping("get/by-id")
    fun getAnswerById(
        @RequestParam id: Long
    ): ResponseEntity<Any> = answerRepository.getAnswerById(id).asResponse()
}
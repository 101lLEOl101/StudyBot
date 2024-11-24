package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.repository.ResultRepository
import backend.studybotbackend.domain.request.result.StartTestRequest
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.RESULT_API)
class ResultController(
    private val resultRepository: ResultRepository
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
    fun getResultById(
        @RequestParam id: Long
    ): ResponseEntity<Any> = resultRepository.getResultById(id).asResponse()


    @GetMapping("get/by-student")
    fun getResultStudent(
        @RequestParam id: Long
    ): ResponseEntity<Any> = resultRepository.getResultsByStudent(id).asResponse()


    @GetMapping("get/by-test")
    fun getResultTest(
        @RequestParam id: Long
    ): ResponseEntity<Any> = resultRepository.getResultsByTest(id).asResponse()

    @PostMapping("post/start")
    fun startTest(
        @RequestBody req: StartTestRequest
    ): ResponseEntity<Any>{
        val state = resultRepository.startTest(
            chatId = req.chatId,
            testId = req.testId,
        )
        return state.asResponse()
    }
}
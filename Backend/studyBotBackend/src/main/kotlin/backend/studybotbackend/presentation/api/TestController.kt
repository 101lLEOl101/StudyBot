package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.repository.TestRepository
import backend.studybotbackend.domain.request.test.CreateTestRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping(Routes.TEST_API)
class TestController(
    private val testRepository: TestRepository
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
    fun getTestById(
        @RequestParam id: Long
    ): ResponseEntity<Any> = testRepository.getTestById(id).asResponse()

    @GetMapping("get/by-discipline")
    fun getTestByDiscipline(
        @RequestParam id: Long
    ): ResponseEntity<Any> = testRepository.getTestsByDiscipline(id).asResponse()

    @GetMapping("get/by-name")
    fun getTestByTestName(
        @RequestParam name: String
    ): ResponseEntity<Any> = testRepository.getTestsByName(name).asResponse()

    @PostMapping("post/create")
    fun createTest(
        @RequestBody testParam: CreateTestRequest
    ):ResponseEntity<Any>{
        val test = Test.new(
            LocalDateTime.now(),
            testParam.expiresTime,
            testParam.discipline,
            testParam.testName,
        )
        val state = testRepository.createTest(test)
        return state.asResponse()
    }

}
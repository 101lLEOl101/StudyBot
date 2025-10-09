package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.model.test.TestFull
import backend.studybotbackend.domain.service.TestService
import backend.studybotbackend.domain.request.test.CreateTestRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping(Routes.TEST_API)
class TestController(
    private val testService: TestService
) {
    @ExceptionHandler(Exception::class, BaseException::class)
    fun exceptionHandler(e: Exception): ResponseEntity<Any> {
        println(e.stackTrace)
        return when (e) {
            is BaseException -> {
                ResponseEntity.status(e.statusCode).body(e)
            }
            else -> {
                ResponseEntity.status(500).body(ServerError(description = e.message))
            }
        }
    }
    @GetMapping("by-id")
    fun getTestById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = testService.getTestById(id).asResponse()

    @GetMapping("by-discipline")
    fun getTestByDiscipline(
        @RequestParam id: Long,
        @RequestParam isAvailable: Boolean = true
    ): ResponseEntity<Any> = testService.getTestsByDiscipline(id,isAvailable).asResponse()

    @GetMapping("by-party")
    fun getTestByParty(
        @RequestParam id: Long,
        @RequestParam isAvailable: Boolean = true
    ): ResponseEntity<Any> = testService.getTestsByParty(id,isAvailable).asResponse()
    @GetMapping("by-name")
    fun getTestByTestName(
        @RequestParam name: String,
        @RequestParam isAvailable: Boolean = true
    ): ResponseEntity<Any> = testService.getTestsByName(name,isAvailable).asResponse()

    @GetMapping("all")
    fun getAllTests(
        @RequestParam isAvailable: Boolean = true
    ): ResponseEntity<Any> {
        val state = testService.getAllTests(isAvailable)
        return state.asResponse()
    }

    @GetMapping("full-by-id")
    fun getFullTest(id: Long): ResponseEntity<Any> {
        val state = testService.getFullTest(id)
        return state.asResponse()
    }

    @PostMapping("create")
    fun createTest(
        @RequestBody testParam: CreateTestRequest
    ):ResponseEntity<Any>{
        val test = Test.new(
            createTime =  LocalDateTime.now(),
            expiresTime =  testParam.expiresTime,
            discipline = testParam.discipline,
            testName = testParam.testName,
        )
        val state = testService.createTest(test)
        return state.asResponse()
    }
    @PostMapping("create-by-tree")
    fun createFullTest(
        @RequestBody testParam: TestFull
    ): ResponseEntity<Any> {
        val state = testService.createFullTest(testParam)
        return state.asResponse()
    }

    @DeleteMapping("by-id")
    fun deleteTest(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = testService.deleteTest(id)
        return state.asResponse()
    }

}
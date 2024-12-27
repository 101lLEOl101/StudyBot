package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.model.test.TestFull
import backend.studybotbackend.domain.repository.TestRepository
import backend.studybotbackend.domain.request.test.CreateTestRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping("by-id")
    fun getTestById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = testRepository.getTestById(id).asResponse()

    @GetMapping("by-discipline")
    fun getTestByDiscipline(
        @RequestParam id: Long,
        @RequestParam isAvailable: Boolean = true
    ): ResponseEntity<Any> = testRepository.getTestsByDiscipline(id,isAvailable).asResponse()

    @GetMapping("by-name")
    fun getTestByTestName(
        @RequestParam name: String,
        @RequestParam isAvailable: Boolean = true
    ): ResponseEntity<Any> = testRepository.getTestsByName(name,isAvailable).asResponse()

    @GetMapping("all")
    fun getAllTests(
        @RequestParam isAvailable: Boolean = true
    ): ResponseEntity<Any> {
        val state = testRepository.getAllTests(isAvailable)
        return state.asResponse()
    }

    @GetMapping("full-by-id")
    fun getFullTest(id: Long): ResponseEntity<Any> {
        val state = testRepository.getFullTest(id)
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
        val state = testRepository.createTest(test)
        return state.asResponse()
    }
    @PostMapping("create-by-tree")
    fun createFullTest(
        @RequestBody testParam: TestFull
    ): ResponseEntity<Any> {
        val state = testRepository.createFullTest(testParam)
        return state.asResponse()
    }

    @DeleteMapping("by-id")
    fun deleteTest(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = testRepository.deleteTest(id)
        return state.asResponse()
    }

}
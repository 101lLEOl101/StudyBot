package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.repository.StudentSubRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.STUDENT_SUB_API)
class StudentSubController(
    private val studentSubRepository: StudentSubRepository
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
    fun getStudentSubById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentSubRepository.getStudentSubById(id).asResponse()

    @GetMapping("get/by-party")
    fun getStudentSubByParty(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentSubRepository.getStudentSubsByParty(id).asResponse()

    @GetMapping("get/by-student")
    fun getStudentSubByStudent(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentSubRepository.getStudentSubsByStudent(id).asResponse()

}
package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.student.Student
import backend.studybotbackend.domain.repository.StudentRepository
import backend.studybotbackend.domain.request.student.CreateStudentRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.STUDENT_API)
class StudentController (
    private val studentRepository: StudentRepository
){
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
    fun getStudentById(
        @RequestParam id: Long,
        ): ResponseEntity<Any> = studentRepository.getStudentById(id).asResponse()

    @GetMapping("by-univercity")
    fun getStudentsByUnivercity(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentRepository.getStudentsByUnivercity(id).asResponse()

    @GetMapping("by-party")
    fun getStudentsByParty(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentRepository.getStudentsByParty(id).asResponse()

    @PostMapping("create")
    fun createStudent(
        @RequestBody studentParam: CreateStudentRequest
    ): ResponseEntity<Any>{
        val student = Student.new(
            chatId = studentParam.chatId,
            nickname = studentParam.nickName,
            university = studentParam.univercity,
        )
        val state = studentRepository.createStudent(student)
        return state.asResponse()
    }

}
package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.studentAnswer.StudentAnswer
import backend.studybotbackend.domain.request.answer.CreateAnswerOptionRequest
import backend.studybotbackend.domain.request.studentAnswer.CreateStudentAnswerRequest
import backend.studybotbackend.domain.service.StudentAnswerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.STUDENT_ANSWER_API)
class StudentAnswerController(
    private val studentAnswerService: StudentAnswerService
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
    fun getAnswerById(
        @RequestParam id: Long
    ): ResponseEntity<Any> = studentAnswerService.getAnswerById(id).asResponse()




    @GetMapping("by-question")
    fun getAnswersByQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> = studentAnswerService.getAnswersByQuestion(id).asResponse()

    @GetMapping("all")
    fun getAllAnswers(): ResponseEntity<Any> {
        val state = studentAnswerService.getAllAnswers()
        return state.asResponse()
    }

    @PostMapping("create")
    fun createQuestion(
        @RequestBody answerParam: CreateStudentAnswerRequest
    ): ResponseEntity<Any> {
        val studentAnswer = StudentAnswer.new(
            .0,
            answerParam.question,
            answerParam.result,
            answerParam.chosenOptions
        )
        val state = studentAnswerService.createAnswer(studentAnswer)
        return state.asResponse()
    }

    @DeleteMapping("by-id")
    fun deleteQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = studentAnswerService.deleteAnswer(id)
        return state.asResponse()
    }


}
package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.question.Question
import backend.studybotbackend.domain.service.QuestionService
import backend.studybotbackend.domain.request.question.CreateQuestionRequest
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
@RequestMapping(Routes.QUESTION_API)
class QuestionController(
    private val questionService: QuestionService
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
    fun getQuestionById(
        @RequestParam id: Long
    ): ResponseEntity<Any> = questionService.getQuestionById(id).asResponse()

    @GetMapping("by-test")
    fun getQuestionByTest(
        @RequestParam id: Long
    ): ResponseEntity<Any> = questionService.getQuestionsByTest(id).asResponse()

    @GetMapping("all")
    fun getAllQuestions(): ResponseEntity<Any> {
        val state = questionService.getAllQuestions()
        return state.asResponse()
    }

    @PostMapping("create")
    fun createQuestion(
        @RequestBody questionParam: CreateQuestionRequest
    ): ResponseEntity<Any> {
        val question = Question.new(
            questionText = questionParam.questionText,
            questionType = questionParam.questionType,
            tests = questionParam.tests
        )
        val state = questionService.createQuestion(question)
        return state.asResponse()
    }

    @DeleteMapping("by-id")
    fun deleteQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = questionService.deleteQuestion(id)
        return state.asResponse()
    }

}
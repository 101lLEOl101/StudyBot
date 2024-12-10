package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.answer.Answer
import backend.studybotbackend.domain.repository.AnswerRepository
import backend.studybotbackend.domain.request.answer.CreateAnswerRequest
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

    @GetMapping("by-id")
    fun getAnswerById(
        @RequestParam id: Long
    ): ResponseEntity<Any> = answerRepository.getAnswerById(id).asResponse()

    @GetMapping("by-result")
    fun getAnswerByResult(
        @RequestParam id: Long
    ): ResponseEntity<Any> = answerRepository.getAnswersByResult(id).asResponse()

    @GetMapping("right-by-question")
    fun getRightAnswerByQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> = answerRepository.getRightAnswersByQuestion(id).asResponse()

    @GetMapping("user-answers-by-question")
    fun getUserAnswerByQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> = answerRepository.getUserAnswersByQuestion(id).asResponse()

    @GetMapping("all")
    fun getAllAnswers(): ResponseEntity<Any> {
        val state = answerRepository.getAllAnswers()
        return state.asResponse()
    }

    @PostMapping("create")
    fun createQuestion(
        @RequestBody answerParam: CreateAnswerRequest
    ): ResponseEntity<Any> {
        val answer = Answer.new(
            isStudentAnswer = answerParam.isStudentAnswer,
            correct = answerParam.correct,
            answerText = answerParam.answerText,
            question = answerParam.question,
        )
        val state = answerRepository.createAnswer(answer)
        return state.asResponse()
    }

    @DeleteMapping("by-id")
    fun deleteQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = answerRepository.deleteAnswer(id)
        return state.asResponse()
    }


}
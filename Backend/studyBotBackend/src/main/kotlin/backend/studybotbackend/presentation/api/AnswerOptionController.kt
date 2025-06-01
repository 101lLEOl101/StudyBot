package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.answerOption.AnswerOption
import backend.studybotbackend.domain.service.AnswerOptionService
import backend.studybotbackend.domain.request.answer.CreateAnswerOptionRequest
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
@RequestMapping(Routes.ANSWER_OPTION_API)
class AnswerOptionController(
    private val answerOptionService: AnswerOptionService
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
    ): ResponseEntity<Any> = answerOptionService.getOptionById(id).asResponse()




    @GetMapping("by-question")
    fun getAnswersByQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> = answerOptionService.getOptionsByQuestion(id).asResponse()

    @GetMapping("all")
    fun getAllAnswers(): ResponseEntity<Any> {
        val state = answerOptionService.getAllOptions()
        return state.asResponse()
    }

    @PostMapping("create")
    fun createQuestion(
        @RequestBody answerParam: CreateAnswerOptionRequest
    ): ResponseEntity<Any> {
        val answerOption = AnswerOption.new(
            correct = answerParam.correct,
            answerText = answerParam.answerText,
            question = answerParam.question,
        )
        val state = answerOptionService.createOption(answerOption)
        return state.asResponse()
    }

    @DeleteMapping("by-id")
    fun deleteQuestion(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = answerOptionService.deleteOption(id)
        return state.asResponse()
    }


}
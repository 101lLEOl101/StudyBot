package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.univercity.University
import backend.studybotbackend.domain.repository.UniversityRepository
import backend.studybotbackend.domain.request.univercity.CreateUnivercityRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.UNIVERSITY_API)
class UniversityController(
    private val universityRepository: UniversityRepository
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
    fun getUniversityById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = universityRepository.getUniversityById(id).asResponse()

    @GetMapping("by-student")
    fun getUniversityByStudent(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = universityRepository.getUniversityByStudent(id).asResponse()

    @PostMapping("create")
    fun createUniversity(
        @RequestBody universityParam:CreateUnivercityRequest
    ): ResponseEntity<Any>{
        val university = University.new(
            universityName = universityParam.universityName
        )
        val state = universityRepository.createUnivercity(university)
        return state.asResponse()
    }
}
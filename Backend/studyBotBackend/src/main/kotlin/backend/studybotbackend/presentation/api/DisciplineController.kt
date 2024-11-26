package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.discipline.Discipline
import backend.studybotbackend.domain.repository.DisciplineRepository
import backend.studybotbackend.domain.request.discipline.CreateDisciplineRequest
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
@RequestMapping(Routes.DISCIPLINE_API)
class DisciplineController(
    private val disciplineRepository: DisciplineRepository
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
    fun getDisciplineById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = disciplineRepository.getDisciplineById(id).asResponse()

    @GetMapping("by-test")
    fun getDisciplineByTest(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = disciplineRepository.getDisciplinesByTest(id).asResponse()

    @GetMapping("by-party")
    fun getDisciplineByParty(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = disciplineRepository.getDisciplinesByParty(id).asResponse()


    @GetMapping("all")
    fun getAllDisciplines(): ResponseEntity<Any>{
        val state = disciplineRepository.getAllDisciplines()
        return state.asResponse()
    }

    @PostMapping("create")
    fun createDiscipline(
        @RequestBody disciplineParam: CreateDisciplineRequest
    ): ResponseEntity<Any> {
        val discipline = Discipline.new(
            disciplineName = disciplineParam.disciplineName
        )
        val state = disciplineRepository.createDiscipline(discipline)
        return state.asResponse()
    }


    @DeleteMapping("by-id")
    fun deleteDicipline(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = disciplineRepository.deleteDiscipline(id)
        return state.asResponse()
    }

}
package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.discipline.Discipline
import backend.studybotbackend.domain.service.DisciplineService
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
    private val disciplineService: DisciplineService
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
    ): ResponseEntity<Any> = disciplineService.getDisciplineById(id).asResponse()

    @GetMapping("by-test")
    fun getDisciplineByTest(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = disciplineService.getDisciplinesByTest(id).asResponse()

    @GetMapping("by-party")
    fun getDisciplineByParty(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = disciplineService.getDisciplinesByParty(id).asResponse()

    @GetMapping("by-student")
    fun getDisciplinesByStudent(
        @RequestParam id: Long,
    )= disciplineService.getDisciplinesByStudent(id).asResponse()


    @GetMapping("all")
    fun getAllDisciplines(): ResponseEntity<Any>{
        val state = disciplineService.getAllDisciplines()
        return state.asResponse()
    }

    @PostMapping("create")
    fun createDiscipline(
        @RequestBody disciplineParam: CreateDisciplineRequest
    ): ResponseEntity<Any> {
        val discipline = Discipline.new(
            disciplineName = disciplineParam.disciplineName
        )
        val state = disciplineService.createDiscipline(discipline)
        return state.asResponse()
    }


    @DeleteMapping("by-id")
    fun deleteDicipline(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = disciplineService.deleteDiscipline(id)
        return state.asResponse()
    }

}
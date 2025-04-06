package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.service.StudentSubService
import backend.studybotbackend.domain.request.studentSub.SubscribeRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Routes.STUDENT_SUB_API)
class StudentSubController(
    private val studentSubService: StudentSubService
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
    fun getStudentSubById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentSubService.getStudentSubById(id).asResponse()

    @GetMapping("by-party")
    fun getStudentSubByParty(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentSubService.getStudentSubsByParty(id).asResponse()

    @GetMapping("by-student")
    fun getStudentSubByStudent(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = studentSubService.getStudentSubsByStudent(id).asResponse()

    @GetMapping("all")
    fun getAllSubs(): ResponseEntity<Any> {
        val state = studentSubService.getAllSubs()
        return state.asResponse()
    }

    @PostMapping("subscribe")
    fun createSub(
        @RequestBody subReq: SubscribeRequest
    ): ResponseEntity<Any>{
        val state = studentSubService.createSubscribe(
            subReq.chatId,
            subReq.partyId
        )
        return state.asResponse()
    }

    @PutMapping("accept-sub")
    fun acceptSub(
        @RequestParam id: Long
    ): ResponseEntity<Any>{
        val state = studentSubService.acceptSub(id)
        return state.asResponse()
    }

    @PutMapping("reject-sub")
    fun rejectSub(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = studentSubService.rejectSub(id)
        return state.asResponse()
    }

    @DeleteMapping("delete")
    fun deleteSub(
        @RequestParam id: Long
    ): ResponseEntity<Any>{
        val state = studentSubService.deleteSub(id)
        return state.asResponse()
    }

}
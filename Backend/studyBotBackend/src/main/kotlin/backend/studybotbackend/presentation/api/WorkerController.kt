package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.worker.Worker
import backend.studybotbackend.domain.service.WorkerService
import backend.studybotbackend.domain.request.worker.CreateWorkerRequest
import backend.studybotbackend.domain.request.worker.SignInRequest
import backend.studybotbackend.domain.request.worker.UpdateWorkerRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Routes.WORKER_API)
class WorkerController(
    private val workerService: WorkerService
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
    fun getWorkerById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = workerService.getWorkerById(id).asResponse()

    @GetMapping("by-party")
    fun getWorkerByParty(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = workerService.getWorkersByParty(id).asResponse()

    @GetMapping("all")
    fun getAllWorkers(): ResponseEntity<Any> = workerService.getAllWorkers().asResponse()

    @PostMapping("sign-in")
    fun signIn(
        @RequestBody req: SignInRequest
    ): ResponseEntity<Any> {
        val state = workerService.signIn(
            nickname = req.nickname,
            password = req.password,
        )
        return state.asResponse()
    }

    @PutMapping("update")
    fun updateWorker(
        @RequestBody req: UpdateWorkerRequest
    ): ResponseEntity<Any> {
        val state = workerService.updateWorker(
            workerId = req.workerId,
            firstName = req.firstName,
            lastName = req.lastName,
            nickName = req.nickName,
            password = req.password,
        )
        return state.asResponse()
    }


    @PostMapping("create")
    fun createWorker(
        @RequestBody workerParams: CreateWorkerRequest
    ): ResponseEntity<Any> {
        val worker =
            Worker.new(
                firstName = workerParams.firstName,
                lastName = workerParams.lastName,
                nickName = workerParams.nickName,
                password = workerParams.password,
                workerRole = workerParams.workerRole,
            )
        val state = workerService.createWorker(worker)
        return state.asResponse()
    }


    @DeleteMapping("by-id")
    fun deleteWorker(
        @RequestParam id: Long
    ): ResponseEntity<Any> {
        val state = workerService.deleteWorker(id)
        return state.asResponse()
    }


}
package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.worker.Worker
import backend.studybotbackend.domain.repository.WorkerRepository
import backend.studybotbackend.domain.request.worker.CreateWorkerRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Routes.WORKER_API)
class WorkerController(
    private val workerRepository: WorkerRepository
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


    @GetMapping("get/by-id")
    fun getWorkerById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = workerRepository.getWorkerById(id).asResponse()

    @GetMapping("get/by-party")
    fun getWorkerByParty(
        @RequestParam id: Long,
    ): ResponseEntity<Any> = workerRepository.getWorkersByParty(id).asResponse()

    @PostMapping("post/create")
    fun createWorker(
        @RequestBody workerParams: CreateWorkerRequest
    ): ResponseEntity<Any> {
        val worker =
            Worker.new(
                firstName =workerParams.firstName,
                lastName =workerParams.lastName,
                nickName =workerParams.nickName,
                password =workerParams.password,
                workerRole =workerParams.workerRole,
            )
        val state = workerRepository.createWorker(worker)
        return state.asResponse()
    }


}
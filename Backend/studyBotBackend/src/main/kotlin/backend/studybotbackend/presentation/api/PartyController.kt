package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.domain.exceptions.BaseException
import backend.studybotbackend.domain.exceptions.ServerError
import backend.studybotbackend.domain.model.party.Party
import backend.studybotbackend.domain.repository.PartyRepository
import backend.studybotbackend.domain.request.party.AddWorkerRequest
import backend.studybotbackend.domain.request.party.CreatePartyRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Routes.PARTY_API)
class PartyController(
    private val partyRepository: PartyRepository
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
    fun getPartyById(
        @RequestParam id: Long,
    ): ResponseEntity<Any> {
        val entity = partyRepository.getPartyById(id)
        return entity.asResponse()
    }

    @GetMapping("by-chat-id")
    fun getPartysByChatId(
        @RequestParam id: Long,
    ): ResponseEntity<Any>{
        val entity = partyRepository.getPartysByStudent(id)
        return entity.asResponse()
    }

    @GetMapping("by-worker-id")
    fun getPartysByWorkerId(
        @RequestParam id: Long,
    ): ResponseEntity<Any>{
        val entity = partyRepository.getPartysByWorker(id)
        return entity.asResponse()
    }

    @GetMapping("by-discipline-id")
    fun getPartysByDisciplineId(
        @RequestParam id: Long,
    ): ResponseEntity<Any>{
        val entity = partyRepository.getPartysByDiscipline(id)
        return entity.asResponse()
    }

    @GetMapping("all")
    fun getAllPartys(): ResponseEntity<Any>{
        val state = partyRepository.getAllPartys()
        return state.asResponse()
    }

    @PostMapping("create")
    fun createParty(
        @RequestBody partyParam: CreatePartyRequest
    ): ResponseEntity<Any>{
        val party = Party.new(
            partyName = partyParam.partyName,
            workers = partyParam.workers,
            disciplines = partyParam.disciplines,
        )
        val state = partyRepository.createParty(party)
        return state.asResponse()
    }

    @PutMapping("add-worker")
    fun addWorker(
        @RequestBody req: AddWorkerRequest
    ): ResponseEntity<Any>{
        val state = partyRepository.addWorker(req.partyId,req.workerId)
        return state.asResponse()
    }




}
package backend.studybotbackend.core.jwt

import backend.studybotbackend.data.dao.WorkerDao
import backend.studybotbackend.data.util.WorkerDomainConverter
import backend.studybotbackend.domain.exceptions.InvalidCredentials
import backend.studybotbackend.domain.service.WorkerService
import jakarta.transaction.Transactional
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrElse

@Component
class WorkerDetailsServise(
    private val workerDao: WorkerDao,
): UserDetailsService, WorkerDomainConverter(){
    @Transactional
    override fun loadUserByUsername(username: String): WorkerDetails {
        val worker = workerDao.findByNickName(username).getOrElse { throw InvalidCredentials() }
        return WorkerDetails(worker.asDomain())
    }
}
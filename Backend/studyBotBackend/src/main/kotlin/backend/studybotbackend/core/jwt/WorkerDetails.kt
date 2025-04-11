package backend.studybotbackend.core.jwt

import backend.studybotbackend.domain.model.worker.Worker
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class WorkerDetails(
    val worker: Worker
): UserDetails {
    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority("ROLE_${worker!!.workerRole}"))
    }

    override fun getPassword(): String {
        return worker.password
    }

    override fun getUsername(): String {
        return worker.nickName
    }


}
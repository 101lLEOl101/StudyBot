package backend.studybotbackend.presentation.api

import backend.studybotbackend.data.dao.UniversityDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping("/health")
class HealthController(val univerDao: UniversityDao) {

    @GetMapping("status")
    fun getStatus(): String {
        return listOf("Это сервак", "Чё каво", "Туда сюда")[(3*Math.random()).toInt()]
    }

    @GetMapping("test")
    fun test(@RequestParam id: Long): String? {
        val a = univerDao.findById(id).getOrNull()?:throw Exception()

        return a.universityName

    }


}
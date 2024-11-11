package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import backend.studybotbackend.data.dao.UniversityDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.jvm.optionals.getOrNull

@RestController
@RequestMapping(Routes.STATUS_API)
class StatusController(val univerDao: UniversityDao) {

    @GetMapping("status")
    fun getStatus(): String {
        return listOf(
            "Это сервак",
            "Чё каво",
            "Туда сюда",
            "халява",
            "ЛАПКА",
            "АХАХХАХАХАХАХ",
            "Настоящее мясо"
        )[(7 * Math.random()).toInt()]
    }

    @GetMapping("test")
    fun test(@RequestParam id: Long): String? {
        val a = univerDao.findById(id).getOrNull() ?: throw Exception()

        return a.universityName

    }


}
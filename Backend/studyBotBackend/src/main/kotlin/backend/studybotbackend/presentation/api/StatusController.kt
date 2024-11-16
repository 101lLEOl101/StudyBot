package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.STATUS_API)
class StatusController() {

    @GetMapping("status")
    fun getStatus(): String {
        return listOf(
            "Это сервак",
            "Чё каво",
            "Туда сюда",
            "халява",
            "ЛАПКА",
            "АХАХХАХАХАХАХ",
            "Настоящее мясо",
            "Лёнчик делает!!!"
        )[(8 * Math.random()).toInt()]
    }


}
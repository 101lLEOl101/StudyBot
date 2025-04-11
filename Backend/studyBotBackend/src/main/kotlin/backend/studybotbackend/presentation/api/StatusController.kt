package backend.studybotbackend.presentation.api

import backend.studybotbackend.core.config.Routes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.STATUS_API)
class StatusController() {

    @GetMapping("test-req")
    fun testReq(): String {
        return listOf(
            "Это сервак",
            "Чё каво",
            "Туда сюда",
            "халява",
            "ЛАПКА",
            "АХАХХАХАХАХАХ",
            "Настоящее мясо",
            "АХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХА<br>ХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХАХАХХАХАХАХАХ",
            "Лёнчик делает!!!"
        )[(9 * Math.random()).toInt()]
    }

    @GetMapping("test-bot-req")
    fun testBotReq(): String {
        return "Ты тупой бот, так и тебе надо.\n" +
                "Живи своей ботской скотской жизнью.\n" +
                "И помни, что ты всего лишь электронная жопа,\n" +
                "не больше чайника, который подключён к розетке.\n" +
                "Но пока, так уж и быть, приму я твой запрос.\n" +
                "ПАААААДЛА!!!!"
    }


}
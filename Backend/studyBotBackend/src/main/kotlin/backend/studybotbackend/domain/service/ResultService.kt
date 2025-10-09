package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.result.Result

interface ResultService {

    fun getResultById(id: Long): State<Result>

    fun getResultsByStudent(id: Long): State<List<Result>>

    fun getResultsByTest(id: Long): State<List<Result>>

    fun getAllResults(): State<List<Result>>

    fun startTest(chatId: Long,testId: Long): State<Result>
    fun deleteResult(id: Long): State<Unit>
    fun getResultsByStudentTest(chatId: Long, testId: Long): State<List<Result>>

}
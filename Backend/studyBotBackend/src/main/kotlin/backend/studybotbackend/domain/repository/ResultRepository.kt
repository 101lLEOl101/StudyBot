package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.entity.ResultEntity
import backend.studybotbackend.domain.model.result.Result

interface ResultRepository {

    fun getResultById(id: Long): State<Result>

    fun getResultsByStudent(id: Long): State<List<Result>>

    fun getResultsByTest(id: Long): State<List<Result>>

}
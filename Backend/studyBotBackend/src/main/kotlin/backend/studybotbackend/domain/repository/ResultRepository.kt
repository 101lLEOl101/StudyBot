package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.result.Result

interface ResultRepository {

    fun getResultById(id: Long): State<Result>
}
package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.test.Test

interface TestRepository {

    fun getTestById(id: Long): State<Test>
}
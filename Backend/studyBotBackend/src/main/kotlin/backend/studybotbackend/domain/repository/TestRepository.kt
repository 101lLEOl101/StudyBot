package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.test.Test
import backend.studybotbackend.domain.model.test.TestFull

interface TestRepository {

    fun getTestById(id: Long): State<Test>

    fun getTestsByDiscipline(id: Long, isAvailable: Boolean): State<List<Test>>

    fun getTestsByName(name: String, isAvailable: Boolean): State<List<Test>>

    fun createTest(test: Test): State<Test>
    fun deleteTest(id: Long): State<Unit>
    fun getAllTests(isAvailable: Boolean): State<List<Test>>
    fun getFullTest(id: Long): State<TestFull>
    fun createFullTest(testParam: TestFull): State<TestFull>
    fun getTestsByParty(id: Long, IsAvailable: Boolean): State<List<Test>>
}
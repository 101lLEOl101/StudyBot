package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.entity.QuestionEntity
import backend.studybotbackend.domain.model.question.Question

interface QuestionRepository {
    fun getQuestionById(id: Long): State<Question>

    fun getQuestionsByTest(id: Long): State<List<Question>>

    fun createQuestion(question: Question): State<Question>
    fun deleteQuestion(id: Long): State<Unit>
}
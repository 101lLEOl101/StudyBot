package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.answer.Answer

interface AnswerRepository {
    fun getAnswerById(id: Long): State<Answer>

    fun getAnswersByResult(id: Long): State<List<Answer>>

    fun getRightAnswersByQuestion(id: Long): State<List<Answer>>

    fun getUserAnswersByQuestion(id: Long): State<List<Answer>>
    fun createAnswer(answer: Answer): State<Answer>

    fun deleteAnswer(id: Long): State<Unit>

    fun getAllAnswers(): State<List<Answer>>
}
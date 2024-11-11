package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.answer.Answer

interface AnswerRepository {
    fun getAnswerById(id: Long): State<Answer>
}
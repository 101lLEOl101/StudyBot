package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.question.Question

interface QuestionService {
    fun getQuestionById(id: Long): State<Question>

    fun getQuestionsByTest(id: Long): State<List<Question>>

    fun getAllQuestions(): State<List<Question>>

    fun createQuestion(question: Question): State<Question>
    fun deleteQuestion(id: Long): State<Unit>
}
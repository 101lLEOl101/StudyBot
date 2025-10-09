package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.answerOption.AnswerOption
import backend.studybotbackend.domain.model.studentAnswer.StudentAnswer

interface StudentAnswerService {
    fun getAnswerById(id: Long): State<StudentAnswer>

    fun getAnswersByQuestion(id: Long): State<List<StudentAnswer>>
    fun createAnswer(studentAnswer: StudentAnswer): State<StudentAnswer>

    fun deleteAnswer(id: Long): State<Unit>

    fun getAllAnswers(): State<List<StudentAnswer>>
}
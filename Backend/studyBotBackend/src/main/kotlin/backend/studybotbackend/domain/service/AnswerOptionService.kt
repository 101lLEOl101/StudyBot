package backend.studybotbackend.domain.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.answerOption.AnswerOption

interface AnswerOptionService {
    fun getOptionById(id: Long): State<AnswerOption>

    fun getOptionsByQuestion(id: Long): State<List<AnswerOption>>
    fun createOption(answerOption: AnswerOption): State<AnswerOption>

    fun deleteOption(id: Long): State<Unit>

    fun getAllOptions(): State<List<AnswerOption>>
}
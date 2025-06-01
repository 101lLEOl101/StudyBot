package backend.studybotbackend.domain.model.studentAnswer

import backend.studybotbackend.data.entity.AnswerOptionEntity
import backend.studybotbackend.data.entity.QuestionEntity
import backend.studybotbackend.data.entity.ResultEntity
import backend.studybotbackend.domain.model.Domain

data class StudentAnswer(
    val id: Long,
    val percentage: Double,
    val question: Long,
    val chosenOptions: MutableList<Long>,
    val result: Long,
    val student: Long,
) : Domain {
    companion object {
        fun new(
            percentage: Double,
            question: Long,
            result: Long,
            chosenOptions: MutableList<Long> = mutableListOf() ,
        ) = StudentAnswer(
            0,
            percentage,
            question,
            chosenOptions,
            result,
            0
        )
    }
}
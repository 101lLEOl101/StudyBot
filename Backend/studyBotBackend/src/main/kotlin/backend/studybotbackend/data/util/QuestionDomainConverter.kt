package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.QuestionEntity
import backend.studybotbackend.domain.model.answer.Question
import backend.studybotbackend.domain.util.DomainConverter

class QuestionDomainConverter : DomainConverter<QuestionEntity, Question> {
    override fun Question.asDatabaseEntity(): QuestionEntity {
        TODO("Not yet implemented")
    }

    override fun QuestionEntity.asDomain(): Question {
        TODO("Not yet implemented")
    }
}
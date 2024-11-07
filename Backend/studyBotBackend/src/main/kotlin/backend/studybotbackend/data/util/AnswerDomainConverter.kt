package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.AnswerEntity
import backend.studybotbackend.domain.model.answer.Answer
import backend.studybotbackend.domain.util.DomainConverter

class AnswerDomainConverter: DomainConverter<AnswerEntity, Answer> {
    override fun Answer.asDatabaseEntity(): AnswerEntity {
        TODO("Not yet implemented")
    }

    override fun AnswerEntity.asDomain(): Answer {
        TODO("Not yet implemented")
    }
}
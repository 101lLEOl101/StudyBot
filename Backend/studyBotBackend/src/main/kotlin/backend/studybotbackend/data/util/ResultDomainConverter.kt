package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.ResultEntity
import backend.studybotbackend.domain.model.answer.Result
import backend.studybotbackend.domain.util.DomainConverter

class ResultDomainConverter : DomainConverter<ResultEntity, Result> {
    override fun Result.asDatabaseEntity(): ResultEntity {
        TODO("Not yet implemented")
    }

    override fun ResultEntity.asDomain(): Result {
        TODO("Not yet implemented")
    }
}
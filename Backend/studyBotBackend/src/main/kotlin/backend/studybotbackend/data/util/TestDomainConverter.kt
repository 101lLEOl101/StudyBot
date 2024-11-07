package backend.studybotbackend.data.util

import backend.studybotbackend.data.entity.TestEntity
import backend.studybotbackend.domain.model.answer.Test
import backend.studybotbackend.domain.util.DomainConverter

class TestDomainConverter : DomainConverter<TestEntity, Test> {
    override fun Test.asDatabaseEntity(): TestEntity {
        TODO("Not yet implemented")
    }

    override fun TestEntity.asDomain(): Test {
        TODO("Not yet implemented")
    }
}
package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.DisciplineDao
import backend.studybotbackend.data.util.DisciplineDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.discipline.Discipline
import backend.studybotbackend.domain.repository.DisciplineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class DisciplineRepositoryImpl : DisciplineRepository, DisciplineDomainConverter() {
    @Autowired
    private lateinit var disciplineDao: DisciplineDao

    override fun getDisciplineById(id: Long): State<Discipline> {
        val entity = disciplineDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }


}
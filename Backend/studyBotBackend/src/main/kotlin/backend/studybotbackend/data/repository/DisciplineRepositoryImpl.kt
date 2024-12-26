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

    override fun getDisciplinesByTest(id: Long): State<List<Discipline>> {
        val entities = disciplineDao.findByTest(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun getDisciplinesByParty(id: Long): State<List<Discipline>> {
        val entities = disciplineDao.findByParty(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createDiscipline(discipline: Discipline): State<Discipline> {
        val entity = disciplineDao.save(discipline.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun getAllDisciplines(): State<List<Discipline>> {
        val entities = disciplineDao.findAll()

        return State.Success(entities.map { it.asDomain() })
    }

    override fun deleteDiscipline(id: Long): State<Unit> {
        val entity = disciplineDao.findById(id).getOrElse { throw NotFoundException() }
        disciplineDao.delete(entity)
        return State.Success(Unit)
    }

    override fun getDisciplinesByStudent(id: Long): State<List<Discipline>> {
        val entities = disciplineDao.findByStudent(id)
        return State.Success(entities.map { it.asDomain() })
    }


}
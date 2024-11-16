package backend.studybotbackend.data.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.UniversityDao
import backend.studybotbackend.data.util.UnivercityDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.univercity.University
import backend.studybotbackend.domain.repository.UniversityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrElse

@Repository
class UniversityRepositoryImpl : UniversityRepository, UnivercityDomainConverter() {
    @Autowired
    private lateinit var universityDao: UniversityDao

    override fun getUniversityById(id: Long): State<University> {
        val entity = universityDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getUniversityByStudent(id: Long): State<University> {
        val entity = universityDao.findByStudent(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }


}
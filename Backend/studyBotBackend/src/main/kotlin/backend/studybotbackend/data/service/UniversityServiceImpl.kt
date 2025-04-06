package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.UniversityDao
import backend.studybotbackend.data.util.UnivercityDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.univercity.University
import backend.studybotbackend.domain.service.StudentService
import backend.studybotbackend.domain.service.UniversityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class UniversityServiceImpl : UniversityService, UnivercityDomainConverter() {
    @Autowired
    private lateinit var universityDao: UniversityDao
@Autowired
private lateinit var studentService: StudentService

    override fun getUniversityById(id: Long): State<University> {
        val entity = universityDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun getUniversityByStudent(id: Long): State<University> {
        val entity = universityDao.findByStudent(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())
    }

    override fun createUnivercity(university: University): State<University> {
        val entity = universityDao.save(university.asDatabaseEntity())
        return State.Success(entity.asDomain())
    }

    override fun deleteUniversity(id: Long): State<Unit> {
        val entity = universityDao.findById(id).getOrElse { throw NotFoundException() }
        entity.students.map { studentService.deleteStudent(it.chatId) }
        universityDao.delete(entity)
        return State.Success(Unit)
    }

    override fun getAllUnivercities(): State<List<University>> {
        val entities = universityDao.findAll()
        return State.Success(entities.map { it.asDomain() })
    }


}
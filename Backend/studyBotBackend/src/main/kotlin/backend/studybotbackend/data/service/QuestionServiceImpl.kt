package backend.studybotbackend.data.service

import backend.studybotbackend.core.util.State
import backend.studybotbackend.data.dao.AnswerOptionDao
import backend.studybotbackend.data.dao.QuestionDao
import backend.studybotbackend.data.dao.StudentAnswerDao
import backend.studybotbackend.data.dao.TestDao
import backend.studybotbackend.data.util.QuestionDomainConverter
import backend.studybotbackend.domain.exceptions.NotFoundException
import backend.studybotbackend.domain.model.question.Question
import backend.studybotbackend.domain.service.QuestionService
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class QuestionServiceImpl(
    private val questionDao: QuestionDao,
    private val answerOptionDao: AnswerOptionDao,
    private val studentAnswerDao: StudentAnswerDao,
    private val testDao: TestDao,
) : QuestionService, QuestionDomainConverter() {
    override fun getQuestionById(id: Long): State<Question> {
        val entity = questionDao.findById(id).getOrElse { throw NotFoundException() }
        return State.Success(entity.asDomain())

    }

    override fun getQuestionsByTest(id: Long): State<List<Question>> {
        val entities = questionDao.findByTest(id).map { it.asDomain() }
        return State.Success(entities)
    }

    override fun createQuestion(question: Question): State<Question> {
        val entity = questionDao.save(question.asDatabaseEntity())
        entity.tests.forEach {
            val testEntity = testDao.findById(it.testId).getOrElse { throw NotFoundException() }
            val questions = testEntity.questions.toMutableList()
            questions.add(entity)
            testEntity.questions = questions
            testDao.save(testEntity)
        }
        return State.Success(entity.asDomain())
    }

    override fun getAllQuestions(): State<List<Question>> {
        val entities = questionDao.findAll()
        return State.Success(entities.map { it.asDomain() })
    }

    override fun deleteQuestion(id: Long): State<Unit> {
        val entity = questionDao.findById(id).getOrElse { throw NotFoundException() }
        answerOptionDao.deleteAll(entity.options)
        studentAnswerDao.deleteAll(entity.studentAnswers)
        questionDao.delete(entity)
        return State.Success(Unit)
    }
}
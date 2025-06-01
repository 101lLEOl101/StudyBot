package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import backend.studybotbackend.domain.model.question.QuestionType
import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class QuestionEntity(
    @Column(length = TextLength.LONG)
    val questionText: String,

    @Column
    val questionType: QuestionType,

    @ManyToMany(mappedBy = "questions")
    val tests: MutableList<TestEntity>,

    @OneToMany(mappedBy = "question")
    var studentAnswers: MutableList<StudentAnswerEntity>,

    @OneToMany(mappedBy = "question")
    val options: MutableList<AnswerOptionEntity>


    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val questionId: Long = 0

    override fun hashCode(): Int {
        return questionId.toInt()
    }
}
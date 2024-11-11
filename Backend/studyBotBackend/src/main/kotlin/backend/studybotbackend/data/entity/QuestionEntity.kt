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
    val tests: List<TestEntity>,

    @OneToMany(mappedBy = "question")
    val answers: List<AnswerEntity>,

    @ManyToMany(mappedBy = "questions")
    val results: List<ResultEntity>,

    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val questionId: Long = 0

}
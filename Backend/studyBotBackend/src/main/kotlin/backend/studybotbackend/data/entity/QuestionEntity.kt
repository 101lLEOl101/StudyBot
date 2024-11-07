package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import backend.studybotbackend.domain.model.question.QuestionType
import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class QuestionEntity(
    @Column(length = TextLength.LONG)
    var questionText: String,

    @ManyToMany(mappedBy = "questions")
    var tests: MutableList<TestEntity>,

    @Column
    var questionType: QuestionType,

    @OneToMany(mappedBy = "question")
    var answers: MutableList<AnswerEntity>,

    @OneToMany(mappedBy = "question")
    var results: MutableList<ResultEntity>,

    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var questionId: Long = 0

}
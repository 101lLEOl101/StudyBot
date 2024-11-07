package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table


@Entity
@Table(name = "answers")
data class AnswerEntity(
    @Column(length = TextLength.MIDLE)
    var correct: String,

    @Column(length = TextLength.MIDLE)
    var answerText: String,

    @ManyToOne
    var question: QuestionEntity,

    ) : DatabaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var answerId: Long = 0
}
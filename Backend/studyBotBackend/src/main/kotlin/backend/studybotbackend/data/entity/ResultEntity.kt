package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "results")
data class ResultEntity(
    @ManyToOne
    var student: StudentEntity,

    @ManyToOne
    var test: TestEntity,

    @ManyToOne
    var question: QuestionEntity,


    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var resultId: Long = 0
}
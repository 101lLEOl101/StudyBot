package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "results")
data class ResultEntity(
    @Column
    val startTime: LocalDateTime,

    @Column
    val finishTime: LocalDateTime,

    @ManyToOne
    val student: StudentEntity,

    @ManyToOne
    val test: TestEntity,

    @ManyToMany
    val questions: List<QuestionEntity>,


    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val resultId: Long = 0
}
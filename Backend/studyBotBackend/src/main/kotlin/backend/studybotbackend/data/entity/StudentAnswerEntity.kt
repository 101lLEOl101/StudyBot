package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "student-answers")
data class StudentAnswerEntity(

    @Column
    val percentage: Double,

    @ManyToOne
    val question: QuestionEntity,

    @ManyToOne
    val result: ResultEntity,

    @ManyToMany(mappedBy = "studentAnswers")
    val chosenOptions: MutableList<AnswerOptionEntity>,

) : DatabaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val studentAnswerId: Long = 0


    override fun hashCode(): Int {
        return studentAnswerId.toInt()
    }
}
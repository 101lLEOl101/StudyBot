package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*


@Entity
@Table(name = "answersOptions")
data class AnswerOptionEntity(

    val correct: Boolean,

    @Column(length = TextLength.MIDLE)
    val answerText: String,

    @ManyToOne
    val question: QuestionEntity,

    @ManyToMany
    @JoinTable(
        name = "answer_option_student_answer",
        joinColumns = [JoinColumn(name = "answer_option_id")],
        inverseJoinColumns = [JoinColumn(name = "student_answer_id")]
    )
    val studentAnswers: MutableList<StudentAnswerEntity>,

    ) : DatabaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val answerOptionId: Long = 0
    override fun hashCode(): Int {
        return answerOptionId.toInt()
    }
}
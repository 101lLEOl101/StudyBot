package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.studentSub.Status
import jakarta.persistence.*


@Entity
@Table(name = "student-sub")
data class StudentSubEntity(
    @Column
    val status: Status,

    @ManyToOne
    val student: StudentEntity,
    @ManyToOne
    val party: PartyEntity,
) : DatabaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val subId: Long = 0
}
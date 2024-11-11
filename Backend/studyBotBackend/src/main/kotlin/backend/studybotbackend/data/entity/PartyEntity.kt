package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*


@Entity
@Table(name = "partys")
data class PartyEntity(
    @Column(length = TextLength.SHORT)
    val partyName: String,
    @ManyToMany
    val workers: List<WorkerEntity>,
    @ManyToMany
    val students: List<StudentEntity>,
    @ManyToMany
    val disciplines: List<DisciplineEntity>,

    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val partyId: Long = 0
}
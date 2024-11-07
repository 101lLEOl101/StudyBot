package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*


@Entity
@Table(name = "partys")
data class PartyEntity(
    @Column(length = TextLength.SHORT)
    var partyName: String,
    @ManyToMany
    var workers: MutableList<WorkerEntity>,
    @ManyToMany
    var students: MutableList<StudentEntity>,
    @ManyToMany
    var disciplines: MutableList<DisciplineEntity>,

    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var partyId: Long = 0
}
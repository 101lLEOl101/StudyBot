package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import backend.studybotbackend.domain.model.worker.Role
import jakarta.persistence.*


@Entity
@Table(name = "workers")
data class WorkerEntity(
    @Column(length = TextLength.SHORT)
    var firstName: String,

    @Column(length = TextLength.SHORT)
    var lastName: String,

    @Column(length = TextLength.SHORT)
    var nickName: String,

    @Column(length = TextLength.SHORT)
    var password: String,

    @Column
    val workerRole: Role,

    @ManyToMany(mappedBy = "workers")
    val partys: List<PartyEntity>,
) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val workerId: Long = 0
}
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

    @ManyToMany(mappedBy = "workers")
    var partys: MutableList<PartyEntity>,

    @Column
    var workerRole: Role,

    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var workerId: Long = 0
}
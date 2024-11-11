package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*


@Entity
@Table(name = "students")
data class StudentEntity(
    @Column(length = TextLength.SHORT)
    val nickname: String,

    @ManyToOne
    val university: UniversityEntity,

    @ManyToMany(mappedBy = "students")
    val partys: List<PartyEntity>,

    @OneToMany(mappedBy = "student")
    val results: List<ResultEntity>,


    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val chatId: Long = 0
}
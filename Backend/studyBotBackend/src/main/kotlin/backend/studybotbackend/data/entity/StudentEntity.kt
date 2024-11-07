package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*


@Entity
@Table(name = "students")
data class StudentEntity(
    @Column(length = TextLength.SHORT)
    var nickname: String,


    @ManyToMany(mappedBy = "students")
    var partys: MutableList<PartyEntity>,

    @ManyToOne
    var university: UniversityEntity,

    @OneToMany(mappedBy = "student")
    var results: MutableList<ResultEntity>,


    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var chatId: Long = 0
}
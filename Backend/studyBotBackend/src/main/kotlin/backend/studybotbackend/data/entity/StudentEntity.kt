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

    @OneToMany(mappedBy = "student")
    val results: List<ResultEntity>,

    @OneToMany(mappedBy = "student")
    val subs: List<StudentSubEntity>,


    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val chatId: Long = 0
}
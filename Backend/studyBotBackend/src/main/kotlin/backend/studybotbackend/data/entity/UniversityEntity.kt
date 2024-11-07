package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*

@Entity
@Table(name = "universities")
data class UniversityEntity(

    @Column(length = TextLength.SHORT)
    var universityName: String,

    @OneToMany(mappedBy = "university")
    var students: MutableList<StudentEntity>
) : DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var universityId: Long = 0
}
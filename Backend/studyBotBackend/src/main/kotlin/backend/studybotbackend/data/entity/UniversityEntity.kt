package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*

@Entity
@Table(name = "universities")
data class UniversityEntity(

    @Column(length = TextLength.SHORT)
    val universityName: String,

    @OneToMany(mappedBy = "university")
    val students: List<StudentEntity>
) : DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val universityId: Long = 0
}
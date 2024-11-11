package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*

@Entity
@Table(name = "disciplines")
data class DisciplineEntity(
    @Column(length = TextLength.SHORT)
    val disciplineName: String,

    @OneToMany(mappedBy = "discipline")
    val tests: List<TestEntity>,

    @ManyToMany(mappedBy = "disciplines")
    val partys: List<PartyEntity>,

    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val disciplineId: Long = 0
}
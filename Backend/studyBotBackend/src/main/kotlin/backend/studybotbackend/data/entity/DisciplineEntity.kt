package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.*

@Entity
@Table(name = "disciplines")
data class DisciplineEntity(
    @Column(length = TextLength.SHORT)
    var disciplineName: String,

    @OneToMany(mappedBy = "discipline")
    var tests: MutableList<TestEntity>,

    @ManyToMany(mappedBy = "disciplines")
    var partys: MutableList<PartyEntity>,

    ) : DatabaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var disciplineId: Long = 0
}
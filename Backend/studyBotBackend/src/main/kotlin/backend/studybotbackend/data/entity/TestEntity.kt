package backend.studybotbackend.data.entity

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.TextLength
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tests")
data class TestEntity(

    @ManyToOne
    var discipline: DisciplineEntity,

    @Column(length = TextLength.SHORT)
    var testName: String,

    @ManyToMany
    var questions: MutableList<QuestionEntity>,

    @OneToMany(mappedBy = "test")
    var results: MutableList<ResultEntity>,

    ) : DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var testId: Long = 0
}
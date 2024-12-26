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
import java.time.LocalDateTime

@Entity
@Table(name = "tests")
data class TestEntity(

    @Column(nullable = false)
    val createTime: LocalDateTime,

    @Column(nullable = false)
    val expiresTime: LocalDateTime,

    @ManyToOne
    val discipline: DisciplineEntity,

    @Column(length = TextLength.SHORT)
    val testName: String,

    @ManyToMany
    var questions: MutableList<QuestionEntity>,

    @OneToMany(mappedBy = "test")
    val results: List<ResultEntity>,

    ) : DatabaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val testId: Long = 0
}
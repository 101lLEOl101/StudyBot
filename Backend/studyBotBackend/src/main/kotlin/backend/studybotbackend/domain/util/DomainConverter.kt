package backend.studybotbackend.domain.util

import backend.studybotbackend.data.model.DatabaseEntity
import backend.studybotbackend.domain.model.Domain

/**
 * DomainConverter нужен для конвертации Domain классов в DatabaseEntity классы
 * Для нужных классов нужно реализовать класс-конвертер
 * Наследуется репозиториями
 */

interface DomainConverter<T : DatabaseEntity, R : Domain> {
    fun R.asDatabaseEntity(): T

    fun T.asDomain(): R
}

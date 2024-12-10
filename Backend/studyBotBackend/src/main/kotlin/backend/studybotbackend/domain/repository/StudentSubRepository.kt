package backend.studybotbackend.domain.repository

import backend.studybotbackend.core.util.State
import backend.studybotbackend.domain.model.studentSub.StudentSub

interface StudentSubRepository {

    fun getStudentSubById(id: Long): State<StudentSub>

    fun getStudentSubsByParty(id: Long): State<List<StudentSub>>

    fun getStudentSubsByStudent(id: Long): State<List<StudentSub>>

    fun createSubscribe(chatId: Long, partyId: Long): State<StudentSub>

    fun acceptSub(subId: Long): State<Boolean>
    fun getAllSubs(): State<List<StudentSub>>

}
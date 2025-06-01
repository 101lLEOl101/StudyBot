package backend.studybotbackend.domain.request.studentAnswer

class CreateStudentAnswerRequest(
    val question: Long,
    val result: Long,
    val chosenOptions: MutableList<Long> = mutableListOf() ,
){

}
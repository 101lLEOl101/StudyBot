class Answers:
    def __init__(self, isStudentAnswer, correct, answerText, question, result):
        self.isStudentAnswer = isStudentAnswer
        self.correct = correct
        self.answerText = answerText
        self.question = question
        self.result = result


class Discipline:
    def __init__(self, disciplineName, tests, partys, disciplineId):
        self.disciplineName = disciplineName
        self.tests = tests
        self.partys = partys
        self.disciplineId = disciplineId


class Party:
    def __init__(self, partyName, workers, disciplines, subs, partyId):
        self.partyName = partyName
        self.workers = workers
        self.disciplines = disciplines
        self.subs = subs
        self.partyId = partyId


class Question:
    def __init__(self, questionText, questionType, tests, answers, questionId):
        self.questionText = questionText
        self.questionType = questionType
        self.tests = tests
        self.answers = answers
        self.questionId = questionId


class Result:
    def __init__(self, startTime, finishTime, student, test, answers, resultId):
        self.startTime = startTime
        self.finishTime = finishTime
        self.student = student
        self.test = test
        self.answers = answers
        self.resultId = resultId


class Student:
    def __init__(self, firstName, lastName, nickname, university, results, subs, chatId):
        self.firstName = firstName
        self.lastName = lastName
        self.nickname = nickname
        self.university = university
        self.results = results
        self.subs = subs
        self.chatId = chatId


class StudentSub:
    def __init__(self, status, student, party, subId):
        self.status = status
        self.student = student
        self.party = party
        self.subId = subId


class Test:
    def __init__(self, createTime, expiresTime, discipline, testName, questions, results, testId):
        self.createTime = createTime
        self.expiresTime = expiresTime
        self.discipline = discipline
        self.testName = testName
        self.questions = questions
        self.results = results
        self.testId = testId


class University:
    def __init__(self, universityName, students, universityId):
        self.universityName = universityName
        self.students = students
        self.universityId = universityId


class Worker:
    def __init__(self, firstName, lastName, nickName, password, workerRole, partys, workerId):
        self.firstName = firstName
        self.lastName = lastName
        self.nickName = nickName
        self.password = password
        self.workerRole = workerRole
        self.partys = partys
        self.workerId = workerId

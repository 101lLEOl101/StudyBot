class Answers:
    def __init__(self, isStudentAnswer, correct, answerText, question, result):
        self.isStudentAnswer = isStudentAnswer
        self.correct = correct
        self.answerText = answerText
        self.question = question
        self.result = result


class Discipline:
    def __init__(self, disciplineName, tests, partys):
        self.disciplineName = disciplineName
        self.tests = tests
        self.partys = partys
        self.disciplineId = 0


class Party:
    def __init__(self, partyName, workers, disciplines, subs):
        self.partyName = partyName
        self.workers = workers
        self.disciplines = disciplines
        self.subs = subs
        self.partyId = 0


class Question:
    def __init__(self, questionText, questionType, tests, answers):
        self.questionText = questionText
        self.questionType = questionType
        self.tests = tests
        self.answers = answers
        self.questionId = 0


class Result:
    def __init__(self, startTime, finishTime, student, test, answers):
        self.startTime = startTime
        self.finishTime = finishTime
        self.student = student
        self.test = test
        self.answers = answers
        self.resultId = 0


class Student:
    def __init__(self, nickname, university, results, subs):
        self.nickname = nickname
        self.university = university
        self.results = results
        self.subs = subs
        self.chatId = 0


class StudentSub:
    def __init__(self, status, student, party):
        self.status = status
        self.student = student
        self.party = party
        self.subId = 0


class Test:
    def __init__(self, createTime, expiresTime, discipline, testName, questions, results):
        self.createTime = createTime
        self.expiresTime = expiresTime
        self.discipline = discipline
        self.testName = testName
        self.questions = questions
        self.results = results
        self.testId = 0


class University:
    def __init__(self, universityName, students):
        self.universityName = universityName
        self.students = students
        self.universityId = 0


class Worker:
    def __init__(self, firstName, lastName, nickName, password, workerRole, partys):
        self.firstName = firstName
        self.lastName = lastName
        self.nickName = nickName
        self.password = password
        self.workerRole = workerRole
        self.partys = partys
        self.workerId = 0

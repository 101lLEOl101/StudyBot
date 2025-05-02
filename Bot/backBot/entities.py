import datetime


class Time:
    pass  # временный класс


class AnswerOption:
    def __init__(self, answerText: str, correct: bool, questionId: int, ownId: int):
        self.answerText = answerText
        self.correct = correct
        self.questionId = questionId
        self.ownId = ownId


class StudentAnswer:
    def __init__(self, percentage: float, questionId: int, chosenOptions: list[int], userId: int, resultId: int,
                 ownId: int):
        self.percentage = percentage
        self.questionId = questionId
        self.chosenOptions = chosenOptions
        self.userId = userId
        self.resultId = resultId
        self.ownId = ownId


class Question:
    def __init__(self, questionText: str, questionType, points: float, tests: list[int], answerOptions: list[int],
                 questionId: int):
        self.questionText = questionText
        self.questionType = questionType
        self.points = points
        self.tests = tests
        self.answerOptions = answerOptions
        self.questionId = questionId


class Result:
    def __init__(self, startTime: Time, finishTime: Time, percentage: float, studentId: int, testId: int,
                 studentAnswers: list[int], ownId: int):
        self.startTime = startTime
        self.finishTime = finishTime
        self.percentage = percentage
        self.studentId = studentId
        self.testId = testId
        self.studentAnswers = studentAnswers
        self.ownId = ownId


class Discipline:
    def __init__(self, disciplineName: str, tests: list[int], partys: list[int], disciplineId: int):
        self.disciplineName = disciplineName  # название дисциплины
        self.tests = tests  # кортеж айдишников тестов
        self.partys = partys  # кортеж айдишников групп
        self.disciplineId = disciplineId  # айди дисциплины


class Party:
    def __init__(self, partyName: str, workers: list[int], disciplines: list[int], subs: list[int], partyId: int):
        self.partyName = partyName
        self.workers = workers
        self.disciplines = disciplines
        self.subs = subs
        self.partyId = partyId


class Question:
    def __init__(self, questionText: str, questionType, tests: list[int], answers: list[int], questionId: int):
        self.questionText = questionText
        self.questionType = questionType
        self.tests = tests
        self.answers = answers
        self.questionId = questionId


class Result:
    def __init__(self, startTime: int, finishTime: int, student: int, test: int, answers: list[int], resultId: int):
        self.startTime = startTime
        self.finishTime = finishTime
        self.student = student
        self.test = test
        self.answers = answers
        self.resultId = resultId


class Student:
    def __init__(self, firstName: str, lastName: str, nickname: str, university: int, results: list[int],
                 subs: list[int], chatId: int):
        self.firstName = firstName
        self.lastName = lastName
        self.nickname = nickname
        self.university = university
        self.results = results
        self.subs = subs
        self.chatId = chatId


class StudentSub:
    def __init__(self, id: int, status: bool, student: int, studentFullName: str, party: int, partyName: str):
        self.id = id
        self.status = status
        self.student = student
        self.studentFullName = studentFullName
        self.party = party
        self.partyName = partyName


class Test:
    def __init__(self, createTime, expiresTime, discipline, testName, questions, results, id):
        self.createTime = createTime
        self.expiresTime = expiresTime
        self.discipline = discipline
        self.testName = testName
        self.questions = questions
        self.results = results
        self.id = id


class University:
    def __init__(self, universityName: str, students: list[int], universityId: int):
        self.universityName = universityName
        self.students = students
        self.universityId = universityId


class Worker:
    def __init__(self, firstName, lastName, nickName, password, workerRole, partys, id):
        self.firstName = firstName
        self.lastName = lastName
        self.nickName = nickName
        self.password = password
        self.workerRole = workerRole
        self.partys = partys
        self.workerId = id


class WholeTest:
    def __init__(self, test: Test, questions: list[Question], results: list[Result], expiresAt: str):
        self.test = test
        self.questions = questions
        self.results = results
        self.expiresAt = expiresAt


class FrontDiscipline:
    def __init__(self, name: str):
        self.name = name


class FrontAnswer:
    def __init__(self, text: str, studentText: str):
        self.text = text
        self.student_text = studentText


class FrontQuestion:
    def __init__(self, text: str, type: int, answers: list[Answers], buttons_text: list[str], is_answered: bool):
        self.text = text
        self.type = type
        self.answers = answers
        self.buttons_text = buttons_text
        self.is_answered = is_answered


class FrontTest:
    def __init__(self, testName: str, start: datetime, end: datetime,
                 questions: list[Question], discipline: Discipline):
        self.testName = testName
        self.start = start
        self.end = end
        self.questions = questions
        self.discipline = discipline

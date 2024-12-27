class Answers:
    def __init__(self, isStudentAnswer: bool, correct: bool, answerText: str, questionId: int, result: bool):  # string,
        self.isStudentAnswer = isStudentAnswer  # есть ответ или нет
        self.correct = correct  # правильный ответ
        self.answerText = answerText  # ответ студента
        self.questionId = questionId  # айди вопроса
        self.result = result  # результат: верный ответ или нет


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
    def __init__(self, status: bool, student: int, party: int, subId: int):
        self.status = status
        self.student = student
        self.party = party
        self.subId = subId


class Test:

    def __init__(self, createTime: int, expiresTime: str, discipline: int, testName: str, questions: list[int],
                 results: list[int], id: int):
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
    def __init__(self, firstName: str, lastName: str, nickName: str, password: str, workerRole, partys: list[int],
                 workerId: int):
        self.firstName = firstName
        self.lastName = lastName
        self.nickName = nickName
        self.password = password
        self.workerRole = workerRole
        self.partys = partys
        self.workerId = workerId


class WholeTest:
    def __init__(self, test: Test, questions: list[Question], results: list[Result], expiresAt : str):
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
    def __init__(self, text: str, type: int, answers: list[Answers], buttons_text:list[str], is_answered:bool):
        self.text = text
        self.type = type
        self.answers = answers
        self.buttons_text = buttons_text
        self.is_answered = is_answered

class FrontTest:
    def __init__(self, testName: str, start, end,
                 questions: list[Question], discipline: Discipline):
        self.testName = testName
        self.start = start
        self.end = end
        self.questions = questions
        self.discipline = discipline

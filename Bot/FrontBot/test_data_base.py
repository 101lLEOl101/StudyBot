from datetime import datetime, date, time

class Discipline:
    def __init__(self, name: str):
        self.name = name 

class Answer:
    def __init__(self, text: str, studentText: str):
        self.text = text
        self.student_text = studentText

class Question:
    def __init__(self, text: str, type: int, answers: list[Answer], buttons_text:list[str], is_answered:bool):
        self.text = text
        self.type = type
        self.answers = answers
        self.buttons_text = buttons_text
        self.is_answered = is_answered

class Test:
    def __init__(self, testName: str, start: datetime, end: datetime,
                 questions: list[Question], discipline: Discipline):
        self.testName = testName
        self.start = start
        self.end = end
        self.questions = questions
        self.discipline = discipline




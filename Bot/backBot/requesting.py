import requests
from entities import *


def set_adress(adr):  # Функция, которая позволяет задать адрес бэка
    global adress
    adress = adr


adress = "http://localhost:8000/"  # ToDo: перед запуском удалять эту строку. Сделано для отладки


def GetTestById(id):  # Конкретный тест по его id
    r = requests.get(f"{adress}api/test/by-id", params={"id": str(id)}).json()
    if r.get("message") != 'success':
        raise KeyError("Test not found.")
    r = r.get("data")
    data = Test(r.get("createTime"), r.get("exporesTime"), r.get("discipline"), r.get("testName"), r.get("questions"),
                r.get("results"), r.get("id"))
    return data


def GetTestByDiscipline(id):  # Возвращает список тестов заданой дисциплины
    r = requests.get(f"{adress}api/test/by-discipline", params={"id": str(id)}).json()
    if r.get("message") != 'success':
        raise KeyError("Test not found.")
    r = r.get("data")
    data = []
    for i in r:
        data += [
            Test(i.get("createTime"), i.get("exporesTime"), i.get("discipline"), i.get("testName"), i.get("questions"),
                 i.get("results"), i.get("id"))]
    return data


def GetTestByName(name):  # Получить тест по его названию
    r = requests.get(f"{adress}api/test/by-name", params={"name": str(name)}).json()
    if r.get("message") != "success":
        raise KeyError("Test not found.")
    r = r.get("data")
    data = Test(r.get("createTime"), r.get("exporesTime"), r.get("discipline"), r.get("testName"), r.get("questions"),
                r.get("results"), r.get("id"))
    return data


def GetStudentById(chatId):  # Получить студента по ID чата
    r = requests.get(f"{adress}api/student/by-id", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    r = r.get("data")
    data = Student(r.get("firstName"), r.get("lastName"), r.get("nickname"), r.get("univercity"), r.get("results"),
                   r.get("subs"), r.get("chatId"))
    return data


def GetStudentGroupsById(chatId):  # Получить список групп студента по его ID
    r = requests.get(f"{adress}api/student/by-id", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    return r.get("data").get("subs")


def GetResultsById(chatId):  # Получить результаты по ID студента
    r = requests.get(f"{adress}api/result/by-student", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    r = r.get("data")
    data = []
    for i in r:
        data += [Result(i.get("startTime"), i.get("finishTime"), i.get("student"), i.get("test"), i.get("answers"),
                      i.get("id"))]
    return data


def SendAnswer(questionId, isStudentAnswer, answerText):  # Создать ответ
    payload = {
        "isStudentAnswer": str(bool(isStudentAnswer)),
        "correct": "true",
        "answerText": str(answerText),
        "question": str(questionId)
    }
    r = requests.post(f"{adress}api/answer/create", json=payload)
    return r.json()


print(GetResultsById(408)[0].startTime)  # ToDo: отладочная строка.

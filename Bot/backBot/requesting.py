import requests


def set_adress(adr):
    global adress
    adress = adr


adress = "http://localhost:8000/"  # ToDo: перед запуском удалять эту строку. Сделано для отладки


def GetTestById(id):  # Конкретный тест по его id
    r = requests.get(f"{adress}api/test/by-id", params={"id": str(id)}).json()
    if r.get("message") != 'success':
        raise KeyError("Test not found.")
    return r


def GetTestByDiscipline(id):  # Возвращает список тестов заданой дисциплины
    r = requests.get(f"{adress}api/test/by-discipline", params={"id": str(id)}).json()
    if r.get("message") != 'success':
        raise KeyError("Test not found.")
    return r


def GetTestByName(name): # Получить тест по его названию
    r = requests.get(f"{adress}api/test/by-name", params={"name": str(name)}).json()
    if r.get("message") != "success":
        raise KeyError("Test not found.")
    return r


def GetStudentById(chatId): # Получить студента по ID чата
    r = requests.get(f"{adress}api/student/by-id", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    return r


def GetStudentGroupsById(chatId): # Получить список групп студента по его ID
    r = requests.get(f"{adress}api/student/by-id", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    return r.get("data").get("subs")


def GetResultsById(chatId): # Получить результаты по ID студента
    r = requests.get(f"{adress}api/result/by-student", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    return r.get("data")


def SendAnswer(questionId, isStudentAnswer, answerText): # Создать ответ
    payload = {
        "isStudentAnswer": str(bool(isStudentAnswer)),
        "correct": "true",
        "answerText": str(answerText),
        "question": str(questionId)
    }
    r = requests.post(f"{adress}api/answer/create", json=payload)
    return r.json()

print(SendAnswer(1, 1, "test")) #ToDo: отладочная строка.

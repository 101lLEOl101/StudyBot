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


def GetTestByName(name):
    r = requests.get(f"{adress}api/test/by-name", params={"name": str(name)}).json()
    if r.get("message") != "success":
        raise KeyError("Test not found.")
    return r


def GetStudentById(chatId):
    r = requests.get(f"{adress}api/student/by-id", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    return r


def GetStudentGroupsById(chatId):
    r = requests.get(f"{adress}api/student/by-id", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    return r.get("data").get("subs")


def GetResultsById(chatId):
    r = requests.get(f"{adress}api/result/by-student", params={"id": str(chatId)}).json()
    if r.get("message") != 'success':
        raise KeyError("Student not found.")
    return r.get("data")

print(GetResultsById(408))

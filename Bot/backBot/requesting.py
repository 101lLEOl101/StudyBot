import requests
from requests import Response

from entities import Answers, Discipline, Party, Question, Result, Student, StudentSub, Test, University, Worker, \
    WholeTest


def set_adress(adr):
    global adress
    global token
    token = "eyJhbGciOiJIUzI1NiIsInRva2VuLXR5cGUiOiJib3QifQ.eyJzdWIiOiJzZXJ2ZXIiLCJpc3MiOiJwbGFuZ2VyLmluYyIsImlhdCI6MTc0NDQ1Njg3MywiZXhwIjoxNzQ3MDQ4ODc0fQ.K05QUFQxJ2RSWDYzMjN-7FKsWPU8Ly1d0OvDkRxxOIQ"
    adress = adr


def Get(addr, parametrs, type):
    param = {}
    header = {
        "Authorization": f"Bearer {token}"
    }
    for i in parametrs:
        param[i[0]] = i[1]
    r = requests.get(addr, params=param, headers=header).json()
    if r.get("message") != 'success':
        raise KeyError(f"{type} not found.")
    r = r.get("data")
    return r


def GetTestById(id):
    r = Get(f"{adress}api/test/by-id", [["id", str(id)]], "TestById")
    return Test(r["createTime"], r["expiresTime"], r["discipline"], r["testName"], r["questions"], r["results"],
                r["id"])


def GetTestByDiscipline(id):
    r = Get(f"{adress}api/test/by-discipline", [["id", str(id)]], "TestByDiscipline")
    return [
        Test(i["createTime"], i["expiresTime"], i["discipline"], i["testName"], i["questions"], i["results"], i["id"])
        for i in r]


def GetTestByName(name):
    r = Get(f"{adress}api/test/by-name", [["name", str(name)]], "TestByName")
    return [
        Test(i["createTime"], i["expiresTime"], i["discipline"], i["testName"], i["questions"], i["results"], i["id"])
        for i in r]


def GetStudentById(chatId):
    r = Get(f"{adress}api/student/by-id", [["id", str(chatId)]], "StudentById")
    return Student(r["firstName"], r["lastName"], r["nickname"], r["university"], r["results"], r["subs"], r["chatId"])


def GetStudentGroupsById(chatId):
    return GetStudentById(chatId).subs


def GetResultsById(chatId):
    return GetStudentById(chatId).results


def SendAnswer(question, isStudentAnswer, answerText):
    payload = {
        "isStudentAnswer": str(bool(isStudentAnswer)),
        "correct": "true",
        "answerText": str(answerText),
        "question": str(question)
    }
    header = {
        "Authorization": f"Bearer {token}"
    }
    r = requests.post(f"{adress}api/answer/create", json=payload, headers=header).json()
    r = r["data"]
    return Answers(r["isStudentAnswer"], r["correct"], r["answerText"], r["question"], r["result"])


def GetWholeTest(id):
    curTest = GetTestById(id)
    questions_ = []
    for qid in curTest.questions:
        r = Get(f"{adress}api/question/by-id", [["id", str(qid)]], "QuestionById")
        questions_.append(Question(r["questionText"], r["questionType"], r["tests"], r["answers"], r["id"]))
    results_ = []
    for rid in curTest.results:
        r = Get(f"{adress}api/result/by-id", [["id", str(rid)]], "ResultById")
        results_.append(Result(r["startTime"], r["finishTime"], r["student"], r["test"], r["answers"], r["id"]))
    return WholeTest(curTest, questions_, results_, curTest.expiresTime)


def GetWorkerById(id):
    r = Get(f"{adress}api/worker/by-id", [["id", str(id)]], "WorkerById")
    return Worker(r["firstName"], r["lastName"], r["nickName"], r["password"], r["workerRole"], r["partys"], r["id"])


def GetAllWorkers():
    r = Get(f"{adress}api/worker/all", [], "WorkerGetAll")
    return [Worker(i["firstName"], i["lastName"], i["nickName"], i["password"], i["workerRole"], i["partys"], i["id"])
            for i in r]


def GetWorkerByParty(id):
    r = Get(f"{adress}api/worker/by-party", [["id", str(id)]], "WorkerByParty")
    return [Worker(i["firstName"], i["lastName"], i["nickName"], i["password"], i["workerRole"], i["partys"], i["id"])
            for i in r]


def GetUnivercityById(id):
    r = Get(f"{adress}api/university/by-id", [["id", str(id)]], "UniversityById")
    return University(r["universityName"], r["students"], r["id"])


def GetUnivercityByStudentId(chatId):
    r = Get(f"{adress}api/university/by-student", [["id", str(chatId)]], "UniversityById")
    return University(r["universityName"], r["students"], r["id"])


def GetDisciplineById(id):
    r = Get(f"{adress}api/discipline/by-id", [["id", str(id)]], "DisciplineById")
    return Discipline(r["disciplineName"], r["tests"], r["partys"], r["id"])


def GetAllDisciplines():
    r = Get(f"{adress}api/discipline/all", [], "DisciplineGetAll")
    return [Discipline(i["disciplineName"], i["tests"], i["partys"], i["id"]) for i in r]


def GetDisciplineByTest(id):
    r = Get(f"{adress}api/discipline/by-test", [["id", str(id)]], "DisciplineByTest")
    return [Discipline(i["disciplineName"], i["tests"], i["partys"], i["id"]) for i in r]


def GetDisciplineByParty(id):
    r = Get(f"{adress}api/discipline/by-party", [["id", str(id)]], "DisciplineByParty")
    return [Discipline(i["disciplineName"], i["tests"], i["partys"], i["id"]) for i in r]


def GetPartyById(id):
    r = Get(f"{adress}api/party/by-id", [["id", str(id)]], "PartyById")
    return Party(r["partyName"], r["workers"], r["disciplines"], r["subs"], r["id"])


def GetAllParties():
    r = Get(f"{adress}api/party/all", [], "PartyGetAll")
    return [Party(i["partyName"], i["workers"], i["disciplines"], i["subs"], i["id"]) for i in r]


def GetStudentByUnivercity(id):
    r = Get(f"{adress}api/student/by-univercity", [["id", str(id)]], "StudentByUnivercity")
    return [Student(i["firstName"], i["lastName"], i["nickname"], i["university"], i["results"], i["subs"], i["chatId"])
            for i in r]


def GetAllStudents():
    r = Get(f"{adress}api/student/all", [], "StudentGetAll")
    return [Student(i["firstName"], i["lastName"], i["nickname"], i["university"], i["results"], i["subs"], i["chatId"])
            for i in r]


def GetStudentByParty(id):
    r = Get(f"{adress}api/student/by-party", [["id", str(id)]], "StudentByParty")
    return [Student(i["firstName"], i["lastName"], i["nickname"], i["university"], i["results"], i["subs"], i["chatId"])
            for i in r]


def GetResultById(id):
    r = Get(f"{adress}api/result/by-id", [["id", str(id)]], "ResultById")
    return Result(r["startTime"], r["finishTime"], r["student"], r["test"], r["answers"], r["id"])


def GetResultByStudent(id):
    r = Get(f"{adress}api/result/by-student", [["id", str(id)]], "ResultByStudent")
    return [Result(i["startTime"], i["finishTime"], i["student"], i["test"], i["answers"], i["id"]) for i in r]


def GetResultByTest(id):
    r = Get(f"{adress}api/result/by-test", [["id", str(id)]], "ResultByTest")
    return [Result(i["startTime"], i["finishTime"], i["student"], i["test"], i["answers"], i["id"]) for i in r]


def StartResult(chatId, testId):
    payload = {
        "chatId": str(chatId),
        "testId": str(testId)
    }
    r = requests.post(f"{adress}api/result/start", json=payload).json()
    r = r["data"]
    return Result(r["startTime"], r["finishTime"], r["student"], r["test"], r["answers"], r["id"])


def GetAnswerById(id):
    r = Get(f"{adress}api/answer/by-id", [["id", str(id)]], "AnswerById")
    return Answers(r["isStudentAnswer"], r["correct"], r["answerText"], r["question"], r["result"])


def GetUserAnswersByQuestions(id):
    r = Get(f"{adress}api/answer/user-answers-by-question", [["id", str(id)]], "AnswerByQuestion")
    return [Answers(i["isStudentAnswer"], i["correct"], i["answerText"], i["question"], i["result"]) for i in r]


def GetAnswerByResult(id):
    r = Get(f"{adress}api/answer/by-result", [["id", str(id)]], "AnswerByResult")
    return [Answers(i["isStudentAnswer"], i["correct"], i["answerText"], i["question"], i["result"]) for i in r]


def GetQuestionByTest(id):
    r = Get(f"{adress}api/question/by-test", [["id", str(id)]], "QuestionByTest")
    return [Question(i["questionText"], i["questionType"], i["tests"], i["answers"], i["id"]) for i in r]


def SubCreate(chatId, partyId):
    payload = {
        "chatId": chatId,
        "partyId": partyId
    }
    header = {
        "Authorization": f"Bearer {token}"
    }
    r = requests.post(f"{adress}api/student-sub/subscribe", json=payload, headers=header).json()
    r = r["data"]
    return StudentSub(r["id"], bool(0 if r["status"] == "NOT_CONSIDERED" else 1), r["student"], r["studentFullName"],
                      r["party"], r["partyName"])


def GetSubById(id):
    r = Get(f"{adress}api/student-sub/by-id", [["id", str(id)]], "SubById")
    return StudentSub(r["id"], bool(0 if r["status"] == "NOT_CONSIDERED" else 1), r["student"], r["studentFullName"],
                      r["party"], r["partyName"])


# === НЕ РАБОТАЕТ ===
# def DeleteSubById(id):
#     header = {
#         "Authorization": f"Bearer {token}"
#     }
#     r = requests.get(f"{adress}api/student-sub/by-id", params={"id": id}, headers=header)
#     if r.status_code != 403:
#         r = r.json()
#     else:
#         return "ERR: 403"
#     if r.get("message") != 'success':
#         raise KeyError(f"{type} not found.")
#     r = r.get("message")
#     return r

def GetAllSubs():
    header = {
        "Authorization": f"Bearer {token}"
    }
    r = requests.get(f"{adress}api/student-sub/all", params=None, headers=header).json()
    r = r["data"]
    lst = []
    for i in r:
        lst += [
            StudentSub(i["id"], bool(0 if i["status"] == "NOT_CONSIDERED" else 1), i["student"], i["studentFullName"],
                       i["party"], i["partyName"])]
    return lst


def SubByStudent(id):
    header = {
        "Authorization": f"Bearer {token}"
    }
    r = Get(f"{adress}api/student-sub/by-student", [["id", id]], "SubByStudent")
    lst = []
    for i in r:
        lst += [
            StudentSub(i["id"], bool(0 if i["status"] == "NOT_CONSIDERED" else 1), i["student"], i["studentFullName"],
                       i["party"], i["partyName"])]
    return lst


def SubByParty(id):
    header = {
        "Authorization": f"Bearer {token}"
    }
    r = Get(f"{adress}api/student-sub/by-party", [["id", id]], "SubByStudent")
    lst = []
    for i in r:
        lst += [
            StudentSub(i["id"], bool(0 if i["status"] == "NOT_CONSIDERED" else 1), i["student"], i["studentFullName"],
                       i["party"], i["partyName"])]
    return lst

set_adress("http://localhost:8000/")
print(SubByParty(1))

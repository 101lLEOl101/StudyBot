import requests


def set_adress(adr):
    global adress
    adress = adr


adress = "http://localhost:8000/"  # ToDo: перед запуском удалять эту строку. Сделано для отладки


def get_test_by_id(id):
    r = requests.get(f"{adress}api/test/by-id", params={"id": str(id)}).json()
    if r.get("message") != 'success':
        raise KeyError("Test not found.")
    return r

print(get_test_by_id(3))
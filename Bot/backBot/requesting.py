import requests

adress = "http://26.37.195.47:8000/"
r = requests.get(f"{adress}api/worker/by-id",
                 params={"id": "8"}).json()  # после айпи и порта надо написать /api ОБЯЗАТЕЛЬНО
print(r)
payload = {
    "firstName": "{{$randomFirstName}}",
    "lastName": "{{$randomLastName}}",
    "nickName": "{{$randomUserName}}",
    "password": "{{$randomPassword}}",
    "workerRole": "0"
}
r = requests.post(f"{adress}api/worker/create", json=payload)  # словарь не data, a json
print(r.text)
r = requests.get(f"{adress}api/worker/by-id", params={"id": "8"}).json()
print(r)

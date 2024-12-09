import {UseFormReturnType} from "@mantine/form";

const baseUrl = "http://localhost:8080";

export function registration(form : UseFormReturnType<{login:string; password:string; repeat_password:string}>){
    const data = {
        login: form.values.login,
        password: form.values.password,
        repeat_password: form.values.repeat_password,
    }
    return fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(data)
    })
}

export function login(form : UseFormReturnType<{login:string; password:string; repeat_password:string}>) {
    const data = {
        login: form.values.login,
        password: form.values.password,
    }
    return fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(data)
    })
}
import {axiosConfig} from "../../axios.ts";
import {UseFormReturnType} from "@mantine/form";


export async function fetchTeachers(){
    return (await axiosConfig.get('/api/worker/all')).data;
}

export async function fetchDisciplines(){
    return (await axiosConfig.get('/api/discipline/all')).data;
}

export async function login(form: UseFormReturnType<{ login: string; password: string }>){
    const body = {
        nickname: form.values.login,
        password: form.values.password,
    };
    return (await axiosConfig.post('/api/worker/sign-in', body)).data;
}

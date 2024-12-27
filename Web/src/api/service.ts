import {axiosConfig} from "../../axios.ts";

export async function fetchTeachers(){
    return (await axiosConfig.get('/api/worker/all')).data;
}

export async function fetchDisciplines(){
    return (await axiosConfig.get('/api/discipline/all')).data;
}

export async function fetchDiscipline(id:number){
    return (await axiosConfig.get(`/api/discipline/by-id?id=${id}`)).data;
}

export async function fetchGroups(){
    return (await axiosConfig.get('/api/party/all')).data;
}

export async function fetchGroup(id:number){
    return (await axiosConfig.get(`/api/party/by-id?id=${id}`)).data;
}

export async function fetchActiveTests(){
    return (await axiosConfig.get('/api/test/all?isAvailable=true')).data;
}

export async function fetchNonActiveTests(){
    return (await axiosConfig.get('/api/test/all?isAvailable=false')).data;
}

export async function fetchAccessStudent(id:number){
    return (await axiosConfig.get(`/api/student-sub/by-party?id=${id}`)).data;
}

export async function fetchGroupInfo(id:number){
    return (await axiosConfig.get(`/api/party/party-info?id=${id}`)).data;
}

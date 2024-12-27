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
    return (await axiosConfig.get('/api/test/all?isAvailable=false')).data;
}
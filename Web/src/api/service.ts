import {axiosConfig} from "../../axios.ts";


export async function fetchTeachers(){
    return (await axiosConfig.get('/api/worker/all')).data;
}
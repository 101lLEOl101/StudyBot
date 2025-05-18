import {axiosConfig} from "../../../axios.ts";
import axios from "axios";
import {CreateWorker, Discipline, Party, Test, TestInfo, Worker} from "../../Interfaces.ts";

export async function fetchTeachers(): Promise<Worker[]> {
    try {
        const res = await axiosConfig.get<{data: Worker[], message: string}>('/api/worker/all');
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке учителей');
        }
        throw new Error('Network error');
    }
}

export async function fetchDisciplines(): Promise<Discipline[]> {
    try {
        const res = await axiosConfig.get<{data: Discipline[], message: string}>('/api/discipline/all');
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке дисциплин');
        }
        throw new Error('Network error');
    }
}

export async function fetchDiscipline(id: number): Promise<Discipline> {
    try {
        const res = await axiosConfig.get<{data: Discipline, message: string}>(`/api/discipline/by-id?id=${id}`);
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке дисциплины');
        }
        throw new Error('Network error');
    }
}

export async function fetchGroups(): Promise<Party[]> {
    try {
        const res = await axiosConfig.get<{data: Party[], message: string}>('/api/party/all');
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке групп');
        }
        throw new Error('Network error');
    }
}

export async function fetchGroup(id: number): Promise<Party> {
    try {
        const res = await axiosConfig.get<{data: Party, message: string}>(`/api/party/by-id?id=${id}`);
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке группы');
        }
        throw new Error('Network error');
    }
}

export async function fetchActiveTests(): Promise<Test[]> {
    try {
        const res = await axiosConfig.get<{data: Test[], message: string}>('/api/test/all?isAvailable=true');
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке активных тестов');
        }
        throw new Error('Network error');
    }
}

export async function fetchNonActiveTests(): Promise<Test[]> {
    try {
        const res = await axiosConfig.get<{data: Test[], message: string}>('/api/test/all?isAvailable=false');
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке неактивных тестов');
        }
        throw new Error('Network error');
    }
}

export async function fetchAccessStudents(id: number): Promise<Worker> {
    try {
        const res = await axiosConfig.get<{data: Worker, message: string}>(`/api/student-sub/by-party?id=${id}`);
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке доступа студентов');
        }
        throw new Error('Network error');
    }
}

export async function fetchGroupInfo(id: number): Promise<TestInfo> {
    try {
        const res = await axiosConfig.get<{data: TestInfo, message: string}>(`/api/party/party-info?id=${id}`);
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка при загрузке информации о группе');
        }
        throw new Error('Network error');
    }
}

export async function registerWorker(input: CreateWorker): Promise<Worker> {
    try {
        const res = await axiosConfig.post<{data: Worker, message: string}>('/api/worker/create', input);
        return res.data.data;
    } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
            throw new Error(error.response?.data?.message || 'Ошибка регистрации');
        }
        throw new Error('Network error');
    }
}

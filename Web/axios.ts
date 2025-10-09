import axios from 'axios';

export const axiosConfig = axios.create({
    baseURL: '/api/'
});

axiosConfig.interceptors.request.use((config) => {
    if (config.url?.includes('/api/auth/login')) {
        return config;
    }

    const token = localStorage.getItem('accessToken');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

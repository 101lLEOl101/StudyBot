import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';
import { AuthTokens, LoginResponse, LoginWorker, CreateWorker, registerWorkerResponse } from './authTypes';
import {axiosConfig} from "../../../axios.ts";

export const userLogin = createAsyncThunk<AuthTokens, LoginWorker, { rejectValue: string }>(
    '/api/auth/login',
    async ({ nickName, password }, { rejectWithValue }) => {
        try {
            const { data } = await axiosConfig.post<LoginResponse>(
                `/api/auth/login`,
                { nickName, password },
                { headers: { 'Content-Type': 'application/json' } }
            );
            const tokens = data.data;
            localStorage.setItem('accessToken', tokens.accessToken);
            localStorage.setItem('refreshToken', tokens.refreshToken);
            return tokens;
        } catch (error) {
            if (axios.isAxiosError(error) && error.response) {
                return rejectWithValue(error.response.data?.message || 'Login failed');
            }
            return rejectWithValue('Network error');
        }
    }
);

export const registerWorker = createAsyncThunk<Worker, CreateWorker, { rejectValue: string }>(
    '/api/worker/create',
    async ({ firstName, lastName, nickName, password }, { rejectWithValue }) => {
        try {
            const config = { headers: { 'Content-Type': 'application/json' } };
            const response = await axiosConfig.post<registerWorkerResponse>(
                `/api/worker/create`,
                { firstName, lastName, nickName, password },
                config
            );
            return response.data.data;
        } catch (error) {
            if (axios.isAxiosError(error) && error.response) {
                return rejectWithValue(error.response.data?.message || 'Registration failed');
            }
            return rejectWithValue('Network error');
        }
    }
);
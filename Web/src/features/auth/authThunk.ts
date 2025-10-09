import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';
import { AuthTokens, LoginResponse, LoginWorker } from './authTypes';
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

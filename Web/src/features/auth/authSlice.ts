import { createSlice } from '@reduxjs/toolkit';
import { AuthState } from './authTypes.ts';

export const initialState: AuthState = {
    loading: false,
    userInfo: null,
    accessToken: null,
    refreshToken: null,
    error: null,
    success: false,
};

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {
        logout: () => {
            initialState.userInfo = null;
            initialState.accessToken = null;
            initialState.refreshToken = null;
            initialState.success = false;
            initialState.error = null;
            localStorage.removeItem('accessToken');
            localStorage.removeItem('refreshToken');
        },
    }
});

export const { logout } = authSlice.actions;
export default authSlice.reducer;
import {configureStore} from "@reduxjs/toolkit";
import authReducer, {initialState} from "../features/auth/authSlice.ts";


const store = configureStore({
    reducer: {
        user: authReducer,
    },
    preloadedState: {
        user: initialState,
    },
})
export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
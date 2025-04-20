import {configureStore} from "@reduxjs/toolkit";
import authSlice from "./slice/authSlice.ts";


const store = configureStore({
    reducer: {
        user: authSlice,
    }
})

export default store;
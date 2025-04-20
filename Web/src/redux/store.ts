import {configureStore} from "@reduxjs/toolkit";
import authSlice from "./api/authSlice.ts";


const store = configureStore({
    reducer: {
        user: authSlice,
    }
})

export default store;
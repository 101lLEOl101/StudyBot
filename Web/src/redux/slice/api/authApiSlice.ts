
import { apiSlice } from "../apiSlice";

export const authApiSlice = apiSlice.injectEndpoints({
    endpoints: (builder) => ({
        login: builder.mutation({
            query: (data) => ({
                url: `auth/login`,
                method: "POST",
                body: data,
                credentials: "include",
            }),
        }),
    }),
});

export const { useLoginMutation } =
    authApiSlice;

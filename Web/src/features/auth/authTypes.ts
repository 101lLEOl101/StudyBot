
export interface LoginWorker{
    nickName: string,
    password: string,
}

export interface AuthTokens{
    accessToken: string,
    refreshToken: string,
}

export interface LoginResponse{
    data: AuthTokens,
    message:string,
}

export interface AuthState {
    loading: boolean,
    userInfo: Worker | null,
    accessToken: string | null,
    refreshToken: string | null,
    error: string | null,
    success: boolean,
}
import { createContext, useContext, useState, useEffect } from 'react';
import { refresh } from '../api/auth';
import { setTokenStore } from '../api/axiosInstance';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [auth, setAuth] = useState({
        accessToken: localStorage.getItem('accessToken'),
        refreshToken: localStorage.getItem('refreshToken'),
        expiresAt: parseInt(localStorage.getItem('expiresAt')) || 0,
        isAuthenticated: !!localStorage.getItem('accessToken')
    });

    useEffect(() => {
        const interval = setInterval(() => {
            if (auth.expiresAt - Date.now() < 5*60*1000) {
                updateTokens();
            }
        }, 60*1000);
        return () => clearInterval(interval);
    }, [auth.expiresAt]);

    const updateTokens = async () => {
        try {
            const response = await refresh();
            const {accessToken, expiresIn } = response.data;
            const expiresAt = Date.now() + expiresIn;
            localStorage.setItem('accessToken', accessToken);
            localStorage.setItem('expiresAt', expiresAt.toString());
            setAuth((prev) => ({ ...prev, accessToken, expiresAt, isAuthenticated: true }));
            return accessToken;
        } catch (error) {
            clearTokens();
            throw new Error("토큰 재발급이 실패하였습니다.");
        }
    };

    const saveTokens = ({ accessToken, refreshToken, expiresIn }) => {
        const expiresAt = Date.now() + expiresIn * 1000;
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
        localStorage.setItem('expiresAt', expiresAt.toString());
        setAuth({ accessToken, refreshToken, expiresAt, isAuthenticated: true });
    };

    const clearTokens = () => {
        localStorage.clear();
        setAuth({ accessToken: null, refreshToken: null, expiresAt: 0, isAuthenticated: false });
    }

    useEffect(() => {
        setTokenStore({ accessToken: auth.accessToken, refreshToken: auth.refreshToken, updateTokens, clearTokens });
    }, [auth.accessToken, auth.refreshToken]);

    return (
        <AuthContext.Provider value={{ auth, saveTokens, clearTokens }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);
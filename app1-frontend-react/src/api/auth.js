import axios from './axiosInstance';
const API_BASE = process.env.REACT_APP_AUTH_API_BASE_URL;

export const signup = async (userData) => {
    const response = await axios.post(`${API_BASE}/signup`, userData);
    return response.data;
}

export const login = async (loginData) => {
    const response = await axios.post(`${API_BASE}/login`, loginData);
    return response.data;
}

export const refresh = async (refreshToken) => {
    const response = await axios.get(`${API_BASE}/refresh`, { params: { token: refreshToken} });
    return response.data;
}

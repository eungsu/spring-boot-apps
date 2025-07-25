import axios from './axiosInstance';
const API_BASE = import.meta.env.VITE_APP_POSTS_API_BASE_URL;

export const createPost = async (postData) => {
    const response = await axios.post(`${API_BASE}`, postData);
    return response.data;
}

export const fetchPosts = async (page = 1) => {
    const response = await axios.get(`${API_BASE}?page=${page}`);
    return response.data;
}

export const fetchPostDetail = async (postNo) => {
    const response = await axios.get(`${API_BASE}/${postNo}`);
    return response.data;
}

export const updatePost = async (postNo, postData) => {
    const response = await axios.put(`${API_BASE}/${postNo}`, postData);
    return response.data;
}

export const deletePost = async (postNo) => {
    const response = await axios.delete(`${API_BASE}/${postNo}`);
    return response.data;
}

export const fetchComments = async (postNo) => {
    const response = await axios.get(`${API_BASE}/${postNo}/comments`);
    return response.data;
}

export const addComment = async (postNo, content) => {
    const response = await axios.post(`${API_BASE}/${postNo}/comments`, { content });
    return response.data;
}

export const deleteComment = async (postNo, commentNo) => {
    const response = await axios.delete(`${API_BASE}/${postNo}/comments/${commentNo}`);
    return response.data;
}

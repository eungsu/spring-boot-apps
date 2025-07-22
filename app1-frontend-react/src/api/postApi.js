import axios from './axiosInstance';
const API_BASE = 'http://localhost:8080/api/posts';

export const fetchPosts = async (page = 1) => {
    const response = await axios.get(`${API_BASE}?page=${page}`);
    return response.data;
}

export const fetchPostDetail = async (postNo) => {
    const response = await axios.get(`${API_BASE}/${postNo}`);
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


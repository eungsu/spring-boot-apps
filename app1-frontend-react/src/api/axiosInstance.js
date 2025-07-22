import axios from 'axios';

const instance = axios.create({
    headers: {
        'Content-Type': 'application/json'
    }
});

let store = {
    accessToken: '',
    refreshToken: '',
    updateTokens: async () => {},
    clearTokens: () => {}
};

export const setTokenStore = (_store) => {
    store = _store;
}

instance.interceptors.request.use(
    (config) => {
        if (store.accessToken) {
            config.headers.Authorization = `Bearer ${store.accessToken}`;
        }
        return config;
    }
);

instance.interceptors.response.use(
    response => response,
    async (error) => {
        const originalRequest = error.config;
        if (error.response?.status === 401 && !originalRequest._retry) {
            originalRequest._retry = true;
            try {
                const accessToken = await store.updateTokens();
                originalRequest.headers.Authorization = `Bearer ${accessToken}`;
                return instance(originalRequest);
            } catch (e) {
                store.clearTokens();
                return Promise.reject(e);
            }
        }
        return Promise.reject(error);
    }
);

export default instance;
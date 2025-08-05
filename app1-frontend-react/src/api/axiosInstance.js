/**
    JWT 인증 토큰 기반의 API 요청 처리를 자동화한 Axios 설정 모듈이다.
    - 요청시 Access Token 자동 첨부 기능 제공
    - 401 응답시 refresh token으로 재발급 후 재요청 기능 제공
    - 토큰 저장소 외부 연결 기능 제공
*/

/*
    Axios import
    - Axios는 HTTP 요청을 처리하는 자바스크립트 라이브러리다.
    - 프론트 엔드 애플리케이션에서 백엔드와 REST API와 통신할 때 사용된다.
*/
import axios from 'axios';

/*
    axios 인스턴스  생성
    - 기본 Content-Type 설정이 되어 있는 axios 인스턴스를 설정한다.
    - 이후 API 호출 시 이 인스턴스를 사용한다.
*/
const instance = axios.create({
    headers: {
        'Content-Type': 'application/json'
    }
});

/*
    store 객체 생성
    - 인증 상태를 외부(context/AuthContext.jsx)에서 주입받기 위한 임시 저장소다.
    - AuthContext에서 로그인 성공 후 토큰 재발급 완료 후 이 값을 채운다.
*/
let store = {
    accessToken: '',
    refreshToken: '',
    updateTokens: async () => {},
    clearTokens: () => {}
};

/*
    setTokenStore 함수 선언
    - 외부에서 store를 주입할 수 있도록 하는 함수다.
*/
export const setTokenStore = (_store) => {
    store = _store;
}

/*
    Request 인터셉터 설정
    - 요청 전 Access Token이 존재하면 Authorization 헤더에 자동으로 추가된다.
    - 모든 요청에 인증이 자동으로 포함된다.
*/
instance.interceptors.request.use(
    (config) => {
        if (store.accessToken) {
            config.headers.Authorization = `Bearer ${store.accessToken}`;
        }
        return config;
    }
);

/*
    Response 인터셉터 설정
    - 401 Unauthorized 에러가 발생한 경우 
    -                                    store.updateTokens() 호출해서 새 Access Token 발급을 시도한다.
    -                                    성공하면 원래 요청에 새 토큰을 넣고 재요청한다.
    -                                    실패하면 store.clearTokens()을 호출해서 로그아웃 처리한다.
*/
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

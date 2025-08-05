/**
    리액트 애플리케이션에서 인증 상태를 전역에서 관리하기 위한 AuthContext 구현
    - 인증 상태 전역 괸리
    - 토큰 저장 및 삭제
    - 토큰 자동 갱신
    - Axios 연동 지원
    - 인증 Hook 제공
*/

/*
    리액트에서 제공하는 기본 Hook 및 Context 관련 함수 import
    - createContext() : 전역 상태를 공유할 수 있는 Context 객체 생성 함수
    - useContext() : 특정 Context의 값을 사용하기 위한 Hook
*/
import { createContext, useContext, useState, useEffect } from 'react';

/*
    인증 관련 import
    - refresh() : Refresh Token을 사용해 새 토큰을 발급하는 함수다.
*/
import { refresh } from '../api/auth';

/*
    axios 요청 인터셉터 설정을 위한 헬퍼함수 import
    - setToeknStore()는 JWT 토큰정보를 외부에서 axiosInstance에 설정한다.
*/
import { setTokenStore } from '../api/axiosInstance';

/*
    Context 생성
    - React Context API를 사용해 인증정보를 전역에서 공유할 수 있도록하기 위해 Context를 생성한다.
*/
const AuthContext = createContext();

/*
    AuthProvider 컴포넌트 선언
        ------------------------------------------------------------------------------
        | export const AuthProvider = ({ children }) => {                            |
        |  ...                                                                       |
        |  return (                                                                  |
        |    <AuthContext.Provider value={{ auth, saveTokens, clearTokens }}>        |
        |        {children}                                                          |
        |   </AuthContext.Provider>                                                  |
        |  );                                                                        |
        | };                                                                         |
        ------------------------------------------------------------------------------
    - 인증 상태와 인증관련 함수를 Context로 공급한다.
*/
export const AuthProvider = ({ children }) => {
    /*
        useState를 활용한 인증 상태 저장
        - accessToken, refreshToken, expiresAt은 localStorage에서 초기값을 읽어 초기상태로 설정한다.
        - isAuthenticated는 accessToken이 존재하는지를 기준으로 로그인 여부를 판정한다.
    */
    const [auth, setAuth] = useState({
        accessToken: localStorage.getItem('accessToken'),
        refreshToken: localStorage.getItem('refreshToken'),
        expiresAt: parseInt(localStorage.getItem('expiresAt')) || 0,
        isAuthenticated: !!localStorage.getItem('accessToken')
    });

    /*
        토큰 자동 갱신 - useEffect(이펙트함수, 의존성값)
        - useEffect + setInterval을 이용해서 만료시간이 임박하면 토큰을 자동으로 갱신하는 타이머을 설정하였다.
        - 이펙트 함수는 토큰의 만료시간이 변경될 때마다 1분마다 실행되는 타이머를 생성한다.
        - 타이머의 콜백함수는 만료시간이 5분 이하로 남으면 updateTokens()을 실행해서 Access Token을 자동 재발급한다.
        - 기존에 설정된 타이머를 제거하는 클린업함수를 반환한다.
        - 클린업 함수는 컴포넌트가 언마운트될 때, auth.expiresAt이 변경되어 useEffect가 다시 실행될 때 자동으로 실행된다.
    */
    useEffect(() => {
        const interval = setInterval(() => {
            if (auth.expiresAt - Date.now() < 5*60*1000) {
                updateTokens();
            }
        }, 60*1000);
        
        // 클린업 함수
        return () => clearInterval(interval);
    }, [auth.expiresAt]);

    /*
        JWT 토큰 정보 업데이트 함수
        - 서버에 refreshToken을 보내서 새 accessToken과 expiresAt을 받아온다.
        - 실패하면 JWT 토큰 정보를 삭제해서 로그아웃 상태로 전환한다.
    */
    const updateTokens = async () => {
        try {
            // 서버에서 토큰 재발급 요청 보내고 응답받기
            const response = await refresh(auth.refreshToken);
            const {accessToken, expiresAt } = response.data;

            // 재발급된 토큰정보를 localStorage에 저장하기
            localStorage.setItem('accessToken', accessToken);
            localStorage.setItem('expiresAt', expiresAt.toString());

            // 재발급된 토큰정보로 State 갱신하기
            setAuth((prev) => ({ ...prev, accessToken, expiresAt, isAuthenticated: true }));
            return accessToken;
        } catch (error) {
            clearTokens();
            throw new Error("토큰 재발급이 실패하였습니다.");
        }
    };

    /*
        JWT 토큰 정보 저장 함수
        - 로그인 성공 시 응답으로 받은 JWT 토큰 정보를 localStorage에 저장한다.
        - State가 관리하는 토큰정보 저장
    */
    const saveTokens = ({ accessToken, refreshToken, expiresAt }) => {
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('refreshToken', refreshToken);
        localStorage.setItem('expiresAt', expiresAt.toString());
        setAuth({ accessToken, refreshToken, expiresAt, isAuthenticated: true });
    };

    /*
        JWT 토큰 정보 삭제 함수
        - 로그아웃 호출시 모든 JWT 토큰정보를 삭제한다.
    */
    const clearTokens = () => {
        localStorage.clear();
        setAuth({ accessToken: null, refreshToken: null, expiresAt: 0, isAuthenticated: false });
    }

    /*
        axios 인터셉터를 위한 토큰/갱신함수 공유
        - setTokenStore()는 axiosInstance 설정 시 사용
    */
    useEffect(() => {
        setTokenStore({
            accessToken: auth.accessToken,
            refreshToken: auth.refreshToken, 
            updateTokens, 
            clearTokens});
    }, [auth.accessToken, auth.refreshToken]);

    return (
        <AuthContext.Provider value={{ auth, saveTokens, clearTokens }}>
            {children}
        </AuthContext.Provider>
    );
};

/*
    useAuth 커스텀훅 정의
    - 어디서든 useAuth()를 호출하면 auth, saveTokens, clearTokens에 접근 가능하다.
*/
export const useAuth = () => useContext(AuthContext);
